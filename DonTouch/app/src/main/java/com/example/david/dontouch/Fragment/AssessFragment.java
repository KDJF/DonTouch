package com.example.david.dontouch.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.david.dontouch.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AssessFragment  extends Fragment {
    private FragmentTabHost mTabHost;

    public AssessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_assess, container, false);
        mTabHost = (FragmentTabHost ) view.findViewById(R.id.tabhost);
        mTabHost.setup(view.getContext(), getChildFragmentManager(), android.R.id.tabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);
        FragmentTabHost.TabSpec tabSpec_1 = mTabHost.newTabSpec("sw").setIndicator("sw");
        FragmentTabHost.TabSpec tabSpec_2 = mTabHost.newTabSpec("tt").setIndicator("tt");
        mTabHost.addTab(tabSpec_1, AppFragment.class, null);
        mTabHost.addTab(tabSpec_2, RankFragment.class, null);

        return view;
    }

}
