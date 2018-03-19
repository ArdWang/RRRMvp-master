package com.ee.project.base.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.ee.project.base.presenter.BasePresenter;
import com.ee.project.base.presenter.view.BaseView;
import com.ee.project.utils.util.PMUtil;

/**
 * Created by rnd on 2018/3/15.
 *
 */

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements BaseView,
        View.OnClickListener{

    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected void init(){
        setContentView(getLayoutResID());
        initView();
        mPresenter = PMUtil.getT(this,0);

        if(this instanceof BaseView){
            mPresenter.setMV(this);
        }
    }

    protected abstract int getLayoutResID();


    protected void initView(){

    }


    @Override
    public void onClick(View v) {

    }
}
