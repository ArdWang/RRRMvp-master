package com.ee.project.utils.util;

import android.content.SharedPreferences;
import android.graphics.Bitmap;

/**
 * Created by Administrator on 2018/3/15.
 *
 */

public class NormalShared {
    public void saveUserImage(SharedPreferences sharedPreferences, Bitmap bitmap,String userid){
        SharedPreferences.Editor corfEditor = sharedPreferences.edit();
        //corfEditor.pu(userid+"UserImage", bitmap);
        corfEditor.commit();
    }
}
