package com.example.david.dontouch.Fragment;


import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.david.dontouch.Model.AppItemInfo;
import com.example.david.dontouch.R;
import com.example.david.dontouch.Util.UStats;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class JournalFragment extends Fragment {

    private FragmentTabHost mTabHost;

    public JournalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_journal, container, false);
        mTabHost = (FragmentTabHost) view.findViewById(R.id.tabhost);
        mTabHost.setup(view.getContext(), getChildFragmentManager(), android.R.id.tabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);
        FragmentTabHost.TabSpec tabSpec_1 = mTabHost.newTabSpec("sw").setIndicator("sw");
        FragmentTabHost.TabSpec tabSpec_2 = mTabHost.newTabSpec("tt").setIndicator("tt");
        mTabHost.addTab(tabSpec_1, AppFragment.class, null);
        mTabHost.addTab(tabSpec_2, MobFragment.class, null);

        return view;
    }



}
