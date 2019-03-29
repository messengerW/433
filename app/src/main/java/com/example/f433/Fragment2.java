package com.example.f433;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

public class Fragment2 extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ArrayList<Fragment> fragmentList;  //放所有的fragment
    ArrayList<String> titleList;// tablayout的标题
    private View view;

    /********************* Tab切换栏begin **********************/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment2, null);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.vp_data);
        Log.e("test", "---------------------onCreateView");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTab();
        Log.e("test", "---------------------test log");
        viewPager.setAdapter(new MAdapter(getFragmentManager()));//getSupportFragmentManager();
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //让viewpager和顶部标题关联
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initTab() {
        fragmentList = new ArrayList<Fragment>();
        titleList = new ArrayList<String>();
        if (fragmentList.size() == 0) {
            fragmentList.add(new Fragment2_Child1());
            fragmentList.add(new Fragment2_Child2());
            fragmentList.add(new Fragment2_Child3());
        }
        if (titleList.size() == 0) {
            titleList.add("Tab1");
            titleList.add("Tab2");
            titleList.add("Tab3");
        }
    }

    private class MAdapter extends FragmentPagerAdapter {
        public MAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        //一定要重写这个返回标题的方法;
        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }

    /*********************** Tab切换栏end *************************/

}