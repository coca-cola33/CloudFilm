package me.jessyan.mvparms.demo.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by phenix on 2017/6/23.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    private List<?> mFragments;
    private List<String> mTitleList;
    public MainPagerAdapter(FragmentManager fm,List<?> mFragments) {
        super(fm);
        this.mFragments=mFragments;
    }

    public MainPagerAdapter(FragmentManager fm, List<?> mFragments, List<String> mTitleList) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitleList = mTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(mTitleList!=null){
            return mTitleList.get(position);
        }else{
            return "";
        }
    }

    public void addFragmentList(List<?> fragment) {
        this.mFragments.clear();
        this.mFragments = null;
        this.mFragments = fragment;
        notifyDataSetChanged();
    }
}
