package com.ee.project.main.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ee.project.R;
import com.ee.project.main.ui.fragment.left.SettingFragment;

/**
 * Created by Administrator on 2018/3/15.
 *
 */

public class LeftMainActivity extends FragmentActivity implements View.OnClickListener{

    private LinearLayout back;

    private TextView titlename;

    private int value;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leftmain);
        initView();
        initData();

    }

    private void initView() {
        back = findViewById(R.id.back);
        titlename = findViewById(R.id.titlename);
        back.setOnClickListener(this);
    }

    private void initData(){
        try{
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            value = bundle.getInt("value");
            showCurrentFragment(value,intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void showCurrentFragment(int index, Intent intent){
        Fragment fragment = null;
        switch (index){
            case 6:
                fragment = new SettingFragment();
                intent = getIntent();
                titlename.setText(intent.getStringExtra("titlename"));
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.currentSet, fragment).commit();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
        }
    }



}
