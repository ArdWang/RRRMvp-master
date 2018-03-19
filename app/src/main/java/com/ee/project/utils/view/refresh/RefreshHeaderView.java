package com.ee.project.utils.view.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.aspsine.swipetoloadlayout.SwipeRefreshHeaderLayout;
import com.ee.project.R;


/**
 * Created by Aspsine on 2015/9/9.
 *
 */
public class RefreshHeaderView extends SwipeRefreshHeaderLayout {

    private ImageView ivArrow;

    private ImageView ivSuccess;

    private TextView tvRefresh;

    private ProgressBar progressBar;

    private int mHeaderHeight;

    private Animation rotateUp;

    private Animation rotateDown;

    private boolean rotated = false;

    private boolean isPullDowning = true;//判断是否正在下拉

    public RefreshHeaderView(Context context) {
        this(context, null);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHeaderHeight = getResources().getDimensionPixelOffset(R.dimen.refresh_header_height);
        rotateUp = AnimationUtils.loadAnimation(context, R.anim.rotate_up);
        rotateDown = AnimationUtils.loadAnimation(context, R.anim.rotate_down);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        tvRefresh = findViewById(R.id.tvRefresh);
        ivArrow = findViewById(R.id.ivArrow);
        ivSuccess = findViewById(R.id.ivSuccess);
        progressBar = findViewById(R.id.progressbar);
    }

    @Override
    public void onRefresh() {
        ivSuccess.setVisibility(GONE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
        tvRefresh.setText("正在加载...");
    }

    @Override
    public void onPrepare() {
        Log.d("TwitterRefreshHeader", "onPrepare()");
    }

    private final String TAG = this.getClass().getSimpleName();

    private int num = 0;

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            num++;
//            Log.i(TAG, "刷新次数：" + num);
            if (ivArrow.getVisibility() != View.VISIBLE) {
                Log.i(TAG, "显示箭头");
                ivArrow.setVisibility(VISIBLE);
                progressBar.setVisibility(GONE);
                ivSuccess.setVisibility(GONE);
            }
            if (y > mHeaderHeight) {
                if (!rotated) {
                    tvRefresh.setText("放开刷新");
                    isPullDowning = true;
                    Log.i(TAG, "触发动画rotateUp：");
                    ivArrow.clearAnimation();
                    ivArrow.startAnimation(rotateUp);
                    rotated = true;
                }
            } else if (y < mHeaderHeight) {
                if (rotated) {
                    Log.i(TAG, "触发动画rotateDown：");
                    ivArrow.clearAnimation();
                    ivArrow.startAnimation(rotateDown);
                    rotated = false;
                }
                if (isPullDowning) {
                    tvRefresh.setText("下拉刷新");
                    isPullDowning = false;
                    Log.i(TAG, "下拉刷新：");
                }
            }
        }
    }

    @Override
    public void onRelease() {
        Log.d("TwitterRefreshHeader", "onRelease()");
    }

    @Override
    public void onComplete() {
        Log.i(TAG, "完成：");
        rotated = false;
        ivSuccess.setVisibility(VISIBLE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
        tvRefresh.setText("完成");
    }

    @Override
    public void onReset() {
        Log.i(TAG, "重置：");
        rotated = false;
        ivSuccess.setVisibility(GONE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
    }

}
