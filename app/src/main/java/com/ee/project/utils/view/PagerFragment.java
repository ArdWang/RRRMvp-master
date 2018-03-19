package com.ee.project.utils.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by rnd on 2018/1/30.
 *
 */

public class PagerFragment extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;

    private List<String> mTitleList;


    public PagerFragment(FragmentManager fm,List<Fragment> mFragmentList,List<String> mTitleList){
        super(fm);
        this.mFragmentList = mFragmentList;
        this.mTitleList = mTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    /**
     * 这个用来显示当前的标题 如果不加 就没有标题的显示
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
