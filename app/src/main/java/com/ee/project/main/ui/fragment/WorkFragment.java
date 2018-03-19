package com.ee.project.main.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ee.project.R;
import com.ee.project.base.ui.fragment.BaseMvpFragment;
import com.ee.project.main.presenter.WorkPresenter;
import com.ee.project.main.presenter.view.WorkView;

/**
 * Created by Administrator on 2018/3/15.
 *
 */

public class WorkFragment extends BaseMvpFragment<WorkPresenter> implements WorkView{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_work,container,false);
        return view;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


}
