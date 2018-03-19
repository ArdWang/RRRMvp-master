package com.ee.project.base.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ee.project.base.presenter.BasePresenter;
import com.ee.project.base.presenter.view.BaseView;
import com.ee.project.utils.util.PMUtil;

/**
 * Created by rnd on 2018/3/15.
 * fragment子类必须要spuer才可以继承他
 *
 */

public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment implements BaseView,
        View.OnClickListener{

    public P mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        init();
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    protected void init(){
        mPresenter = PMUtil.getT(this,0);
        if(this instanceof BaseView){
            mPresenter.setMV(this);
        }
    }


    @Override
    public void onClick(View v) {

    }
}
