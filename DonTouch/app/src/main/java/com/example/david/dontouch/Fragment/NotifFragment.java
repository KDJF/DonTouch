package com.example.david.dontouch.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.dontouch.Activity.CountDownActivity;
import com.example.david.dontouch.Activity.CountTimeDownActivity;
import com.example.david.dontouch.R;
import com.example.david.dontouch.View.NotifProgressView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.ielse.view.SwitchView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotifFragment  extends Fragment {
    private ListView listView;
    private NotifProgressView notifProgressView;
    private List<Map<String, Object>> mData;
    private Button button;

    public NotifFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notif, container, false);
        listView = (ListView) view.findViewById(R.id.notif_list);
        notifProgressView = (NotifProgressView) view.findViewById(R.id.notif_progress);
        notifProgressView.setProgress(66);
        mData = getData();
        MyAdapter adapter = new MyAdapter(getContext());
        listView.setAdapter(adapter);
        listView.setEnabled(false);
        button = (Button) view.findViewById(R.id.notif_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), CountTimeDownActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "每日额度");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "通知");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "高级提醒");
        list.add(map);

        return list;
    }

    public final class ViewHolder{
        public TextView title;
        public SwitchView viewBtn;
    }

    public class MyAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        public MyAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mData.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return mData.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {

                holder = new ViewHolder();

                convertView = mInflater.inflate(R.layout.notif_item, null);
                holder.title = (TextView)convertView.findViewById(R.id.notif_item_text);
                holder.viewBtn = (SwitchView) convertView.findViewById(R.id.switch_view);
                convertView.setTag(holder);

            }else {

                holder = (ViewHolder)convertView.getTag();
            }


            holder.title.setText((String)mData.get(position).get("title"));

            holder.viewBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (position == 0) {
                        notifProgressView.setTotalMin(40);
                    } else if (position == 1) {
                        Log.i("b", "bbbbbbbbbb");
                    }
                }
            });

            return convertView;
        }

    }

}
