package com.ee.project.main.ui.fragment.left;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.ee.project.R;

/**
 * Created by rnd on 2018/3/16.
 * 设置界面
 */

public class SettingFragment extends Fragment implements View.OnClickListener{
    private TextView mUserName;
    private Button mBtnOut;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting,container,false);
        initView(view);
        return view;
    }

    private void initView(View view){
        mUserName = view.findViewById(R.id.mUserName);
        mBtnOut = view.findViewById(R.id.mBtnOut);
        mBtnOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.mBtnOut:

                break;
        }
    }
}
