package com.ee.project.main.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ee.project.R;
import com.ee.project.base.ui.fragment.BaseMvpFragment;
import com.ee.project.check.ui.activity.CheckActivity;
import com.ee.project.main.adapter.OperationAdapter;
import com.ee.project.main.bean.OpDataBean;
import com.ee.project.main.presenter.HomePresenter;
import com.ee.project.main.presenter.view.HomeView;
import com.ee.project.utils.util.BannerImageConfig;
import com.ee.project.utils.util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/15.
 *
 */

public class HomeFragment extends BaseMvpFragment<HomePresenter> implements HomeView,OperationAdapter.OnItemClickListener{

    private Banner banner;
    List<String> list = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    private OperationAdapter operationAdapter;
    private RecyclerView mOperationView;
    private List<OpDataBean> oplist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        initData();
        initView(view);
        return view;
    }

    //配置数据
    private void initData(){
        oplist = new ArrayList<>();
        OpDataBean opDataBean1 = new OpDataBean(R.drawable.lone,"审查");
        oplist.add(opDataBean1);
        OpDataBean opDataBean2 = new OpDataBean(R.drawable.lone,"审查");
        oplist.add(opDataBean2);
        OpDataBean opDataBean3 = new OpDataBean(R.drawable.lone,"审查");
        oplist.add(opDataBean3);
        OpDataBean opDataBean4 = new OpDataBean(R.drawable.lone,"审查");
        oplist.add(opDataBean4);
        OpDataBean opDataBean5 = new OpDataBean(R.drawable.lone,"审查");
        oplist.add(opDataBean5);
        OpDataBean opDataBean6 = new OpDataBean(R.drawable.lone,"审查");
        oplist.add(opDataBean6);
        OpDataBean opDataBean7 = new OpDataBean(R.drawable.lone,"审查");
        oplist.add(opDataBean7);
        OpDataBean opDataBean8 = new OpDataBean(R.drawable.lone,"审查");
        oplist.add(opDataBean8);
        OpDataBean opDataBean9 = new OpDataBean(R.drawable.lone,"审查");
        oplist.add(opDataBean9);
    }

    private void initView(View view){
        list.add(BannerImageConfig.bannerImage1);
        list.add(BannerImageConfig.bannerImage2);
        list.add(BannerImageConfig.bannerImage3);
        list.add(BannerImageConfig.bannerImage4);

        titles.add(BannerImageConfig.bannerTitle1);
        titles.add(BannerImageConfig.bannerTitle2);
        titles.add(BannerImageConfig.bannerTitle3);
        titles.add(BannerImageConfig.bannerTitle4);

        banner = view.findViewById(R.id.banner);
        mOperationView = view.findViewById(R.id.mOperationView);
        mOperationView.setLayoutManager(new GridLayoutManager(getActivity(),4));


        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(list);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        //设置点击事件，下标是从1开始
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getActivity(), "你点击了：" + position, Toast.LENGTH_SHORT).show();
            }
        });

        if(operationAdapter==null){
            operationAdapter = new OperationAdapter(getActivity(),oplist,this);
            mOperationView.setAdapter(operationAdapter);
        }else{
            operationAdapter.oplist = oplist;
            operationAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    @Override
    public void OnItemClick(View view, int pos) {
        switch (pos){
            case 0:
                Intent intent = new Intent(getActivity(), CheckActivity.class);
                startActivity(intent);
                break;
        }
        //点击跳转
        Toast.makeText(getActivity(), "你点击了：" + pos, Toast.LENGTH_SHORT).show();
    }
}
