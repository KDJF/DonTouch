package com.example.david.dontouch.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.david.dontouch.Model.AppItemInfo;
import com.example.david.dontouch.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankFragment extends Fragment {
    private ListView listview;
    private baseAdapter mAdapter;

    public RankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rank, container, false);
        return view;
    }

    private class baseAdapter extends BaseAdapter {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        private ArrayList<AppItemInfo> itemlist = null ;
        public baseAdapter(ArrayList<AppItemInfo> list){
            this.itemlist = list ;
        }
        @Override
        public int getCount() {
            return itemlist.size();
        }
        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                // 使用View的对象itemView与R.layout.item关联
                convertView = inflater.inflate(R.layout.list_item, null);
                holder = new ViewHolder();
                holder.icon = (ImageView) convertView
                        .findViewById(R.id.apps_image);
                holder.label = (TextView) convertView
                        .findViewById(R.id.apps_name);
                holder.time = (TextView) convertView
                        .findViewById(R.id.apps_time);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.icon.setImageDrawable(itemlist.get(position).getIcon());
            holder.label.setText(itemlist.get(position).getLabel().toString());
            holder.time.setText(itemlist.get(position).getStartTime()+"毫秒");
            return convertView;
        }
    }
    private class ViewHolder {
        private ImageView icon;
        private TextView label;
        private TextView time;
    }

}
