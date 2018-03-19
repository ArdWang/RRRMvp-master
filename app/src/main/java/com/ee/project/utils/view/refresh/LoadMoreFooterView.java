package com.ee.project.utils.view.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.aspsine.swipetoloadlayout.SwipeLoadMoreFooterLayout;
import com.ee.project.R;


/**
 * Created by Aspsine on 2015/9/2.
 *
 */
public class LoadMoreFooterView extends SwipeLoadMoreFooterLayout {

    private final String TAG = this.getClass().getSimpleName();

    private TextView tvLoadMore;
    private ImageView ivSuccess;
    private ProgressBar progressBar;
    private int mFooterHeight;

    /**
     * 是否正在上拉
     */
    private boolean isPullUp = false;
    /**
     * 是否松开
     */
    private boolean isRelease = false;

    public LoadMoreFooterView(Context context) {
        this(context, null);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mFooterHeight = getResources().getDimensionPixelOffset(R.dimen.load_more_footer_height_classic);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvLoadMore = findViewById(R.id.tvLoadMore);
        ivSuccess = findViewById(R.id.ivSuccess);
        progressBar = findViewById(R.id.progressbar);
    }

    @Override
    public void onPrepare() {
        ivSuccess.setVisibility(GONE);
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
//        Log.i(TAG, "y: " + y + ",isComplete:" + isComplete + ",automatic:" + automatic);
        if (!isComplete) {
            if (ivSuccess.getVisibility() == View.VISIBLE) {
                Log.i(TAG, "隐藏progressBar: ");
                ivSuccess.setVisibility(GONE);
                progressBar.setVisibility(GONE);
            }
            if (-y >= mFooterHeight) {
                if (!isRelease) {
                    isRelease = true;
                    isPullUp = false;
                    Log.i(TAG, "onMove: 1111");
                    tvLoadMore.setText("放开加载更多");
                }
            } else {
                if (!isPullUp) {
                    isPullUp = true;
                    isRelease = false;
                    Log.i(TAG, "onMove: 22222");
                    tvLoadMore.setText("上拉加载更多数据");
                }
            }
        }
    }

    @Override
    public void onLoadMore() {
        tvLoadMore.setText("正在加载");
        progressBar.setVisibility(VISIBLE);
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        progressBar.setVisibility(GONE);
        ivSuccess.setVisibility(VISIBLE);
    }

    @Override
    public void onReset() {
        ivSuccess.setVisibility(GONE);
    }
}
