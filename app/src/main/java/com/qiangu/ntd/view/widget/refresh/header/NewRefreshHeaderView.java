package com.qiangu.ntd.view.widget.refresh.header;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.qiangu.ntd.R;

/**
 * Created by Administrator on 2017/8/14.
 */

public class NewRefreshHeaderView extends RelativeLayout
        implements SwipeTrigger, SwipeRefreshTrigger {

    private ImageView ivRefresh;

    private AnimationDrawable mAnimDrawable;


    public NewRefreshHeaderView(Context context) {
        super(context);
    }


    public NewRefreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public NewRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        ivRefresh = (ImageView) findViewById(R.id.ivRefresh);
       // ivRefresh.setImageResource(R.drawable.new_header_animation);
        mAnimDrawable = (AnimationDrawable) ivRefresh.getDrawable();
    }


    @Override public void onRefresh() {
        mAnimDrawable.start();
        if (!mAnimDrawable.isRunning()) {
            mAnimDrawable.start();
        }
    }


    @Override public void onPrepare() {

    }


    @Override public void onMove(int y, boolean isComplete, boolean automatic) {
    }


    @Override public void onRelease() {
        if (!mAnimDrawable.isRunning()) {
            mAnimDrawable.start();
        }
    }


    @Override public void onComplete() {
    }


    @Override public void onReset() {
        mAnimDrawable.stop();
    }
}
