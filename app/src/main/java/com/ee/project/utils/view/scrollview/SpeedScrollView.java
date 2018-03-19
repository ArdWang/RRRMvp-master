package com.ee.project.utils.view.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by rnd on 2018/3/16.
 * 可以切换快速滑动或者慢速滑动
 */

public class SpeedScrollView extends ScrollView {
    public SpeedScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public SpeedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public SpeedScrollView(Context context) {
        super(context);
    }
    /**
     * 滑动事件
     */
    @Override
    public void fling(int velocityY) {
        super.fling(velocityY / 4);
    }
}
