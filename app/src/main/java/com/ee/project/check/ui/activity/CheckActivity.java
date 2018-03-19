package com.ee.project.check.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import com.ee.project.R;
import com.ee.project.check.ui.fragment.CheckAllFragment;
import com.ee.project.check.ui.fragment.CheckShFragment;
import com.ee.project.check.ui.fragment.CheckSpFragment;
import com.ee.project.utils.view.NoScrollViewPager;
import com.ee.project.utils.view.PagerFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by rnd on 2018/3/16.
 * 审查模块
 */

public class CheckActivity extends FragmentActivity{
    private TabLayout mTabLayout;
    private NoScrollViewPager mViewPager;
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private List<Fragment> mFragmentList = new ArrayList<>();//页卡视图集合

    /**
     * 这里最好该换成fragment 可以做5个
     */
    private Fragment all,sp,sh; //页卡视图


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        initView();

    }

    private void initView(){
        mViewPager = findViewById(R.id.check_view);
        mTabLayout = findViewById(R.id.check_tab);

        mViewPager.setNoScroll(true);

        all = new CheckAllFragment();
        sp = new CheckSpFragment();
        sh = new CheckShFragment();

        mFragmentList.add(all);
        mFragmentList.add(sp);
        mFragmentList.add(sh);

        //添加页卡标题
        mTitleList.add("所有订单");
        mTitleList.add("待审批订单");
        mTitleList.add("待审核订单");

        PagerFragment mAdapter = new PagerFragment(getSupportFragmentManager(),mFragmentList,mTitleList);
        //myFragmentPagerAdapter = new MyFragmentPagerAdapter(ma.getSupportFragmentManager(),mViewList);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        //mTabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器
        mTabLayout.setupWithViewPager(mViewPager);
        //设置viewpager的缓存页面的个数
        /**
         * 完美解决重复加载的问题
         * 添加以下2段代码即可
         */
        mViewPager.setOffscreenPageLimit(6);
        mViewPager.setAdapter(mAdapter);
    }
}
