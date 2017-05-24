package com.example.david.dontouch.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.david.dontouch.Model.RankItemInfo;
import com.example.david.dontouch.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankFragment extends Fragment {
    private ArrayList<RankItemInfo> list;
    private ListView listview;
    private baseAdapter mAdapter;
    private View view;

    public RankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_rank, container, false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }

        listview = (ListView) view.findViewById(R.id.list_friend);

        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            RankItemInfo rankItemInfo = new RankItemInfo();
            rankItemInfo.setId(i + 1);
            rankItemInfo.setIcon(R.drawable.ic_launcher);
            rankItemInfo.setName("Android");
            rankItemInfo.setLikes(0);
            rankItemInfo.setTime(5000);
            list.add(rankItemInfo);
        }
        mAdapter = new baseAdapter(list) ;
        listview.setAdapter(mAdapter);
        return view;
    }

    private class baseAdapter extends BaseAdapter {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        private ArrayList<RankItemInfo> itemlist = null ;
        public baseAdapter(ArrayList<RankItemInfo> list){
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
                convertView = inflater.inflate(R.layout.rank_list_item, null);
                holder = new ViewHolder();
                holder.id = (TextView) convertView
                        .findViewById(R.id.rank_id);
                holder.icon = (CircleImageView) convertView
                        .findViewById(R.id.rank_icon);
                holder.label = (TextView) convertView
                        .findViewById(R.id.rank_name);
                holder.time = (TextView) convertView
                        .findViewById(R.id.rank_time);
                holder.likenum = (TextView) convertView
                        .findViewById(R.id.likes_num);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.id.setText(Integer.toString(itemlist.get(position).getId()));
            holder.icon.setImageResource(itemlist.get(position).getIcon());
            holder.label.setText(itemlist.get(position).getName().toString());
            holder.time.setText(itemlist.get(position).getTime()+"毫秒");
            holder.likenum.setText(Integer.toString(itemlist.get(position).getLikes()));
            return convertView;
        }
    }
    private class ViewHolder {
        private TextView id;
        private CircleImageView icon;
        private TextView label;
        private TextView time;
        private TextView likenum;
    }

}
