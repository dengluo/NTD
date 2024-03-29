package com.qiangu.ntd.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.core.widget.NestedScrollView;

public class ObservableNestedScrollView extends NestedScrollView {

    public ObservableNestedScrollView(Context context) {
        super(context);
    }


    public ObservableNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public ObservableNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 回调接口监听事件
     */
    private OnObservableNestedScrollViewListener
            mOnObservableNestedScrollViewListener;

    /**
     * 添加回调接口，便于把滑动事件的数据向外抛
     */
    public interface OnObservableNestedScrollViewListener {
        void onObservableScrollViewListener(int l, int t, int oldl, int oldt);
    }

    /**
     * 注册回调接口监听事件
     *
     * @param onObservableNestedScrollViewListener
     */
    public void setOnObservableNestedScrollViewListener(OnObservableNestedScrollViewListener onObservableNestedScrollViewListener) {
        this.mOnObservableNestedScrollViewListener
                = onObservableNestedScrollViewListener;
    }

    /**
     * 滑动监听
     * This is called in response to an internal scroll in this view (i.e., the
     * view scrolled its own contents). This is typically as a result of
     * {@link #scrollBy(int, int)} or {@link #scrollTo(int, int)} having been
     * called.
     *
     * @param l Current horizontal scroll origin. 当前滑动的x轴距离
     * @param t Current vertical scroll origin. 当前滑动的y轴距离
     * @param oldl Previous horizontal scroll origin. 上一次滑动的x轴距离
     * @param oldt Previous vertical scroll origin. 上一次滑动的y轴距离
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnObservableNestedScrollViewListener != null) {
            //将监听到的数据向外抛
            mOnObservableNestedScrollViewListener.onObservableScrollViewListener(l, t, oldl, oldt);
        }
    }


}
