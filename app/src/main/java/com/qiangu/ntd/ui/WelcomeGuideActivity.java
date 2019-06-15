package com.qiangu.ntd.ui;

import android.os.Bundle;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
import com.qiangu.ntd.base.BaseActivity;

/**
 * Created by Administrator on 2016/8/25.
 */
public class WelcomeGuideActivity extends BaseActivity
        implements ViewPager.OnPageChangeListener {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    @Override public void onPageSelected(int position) {

    }


    @Override public void onPageScrollStateChanged(int state) {

    }


    @Override protected void getBundleExtras(Bundle extras) {

    }


    @Override protected int getContentViewLayoutID() {
        return 0;
    }


    @Override protected View getLoadingTargetView() {
        return null;
    }


    @Override protected void initView(Bundle savedInstanceState) {

    }


    @Override protected void initToolbar() {

    }


    @Override protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }
    //@BindView(R.id.viewPager) ViewPager mViewPager;
    //@BindView(R.id.btnGo) Button mBtnGo;
    ////@BindView(R.id.tvVersion) TextView mTvVersion;
    //@BindView(R.id.guide_ll_point) LinearLayout mGuideLlPoint;//放置圆点
    //// 放引导页图片的ImageView的list
    //private GuideAdapter adapter;
    //private int[] imageIdArray;//图片资源的数组
    //private List<View> viewList;//图片资源的集合
    ////实例化原点View
    //private ImageView iv_point;
    //private ImageView[] ivPointArray;
    //
    //
    //@Override protected void getBundleExtras(Bundle extras) {
    //
    //}
    //
    //
    //@Override protected int getContentViewLayoutID() {
    //    return R.layout.activity_guide;
    //}
    //
    //
    //@Override protected View getLoadingTargetView() {
    //    return null;
    //}
    //
    //
    //@Override protected void initView(Bundle savedInstanceState) {
    //    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
    //            WindowManager.LayoutParams.FLAG_FULLSCREEN);
    //    //加载ViewPager
    //    initViewPager();
    //    //加载底部圆点
    //    initPoint();
    //    //mTvVersion.setText("v"+APP_VERSION_NAME);
    //}
    //
    //
    //@Override protected void initToolbar() {
    //
    //}
    //
    //
    //
    //@Override protected TransitionMode getOverridePendingTransitionMode() {
    //    return null;
    //}
    //
    //
    //@OnClick({ R.id.btnGo, R.id.ibtClose }) public void onClick(View view) {
    //    switch (view.getId()) {
    //        case R.id.btnGo:
    //            startActivity(new Intent(WelcomeGuideActivity.this,
    //                    MainActivity.class));
    //            finish();
    //            break;
    //        case R.id.ibtClose:
    //            startActivity(new Intent(WelcomeGuideActivity.this,
    //                    MainActivity.class));
    //            finish();
    //            break;
    //    }
    //}
    //
    //
    ///**
    // * 加载底部圆点
    // */
    //private void initPoint() {
    //    //根据ViewPager的item数量实例化数组
    //    ivPointArray = new ImageView[viewList.size()];
    //    //循环新建底部圆点ImageView，将生成的ImageView保存到数组中
    //    int size = viewList.size();
    //    for (int i = 0; i < size; i++) {
    //        iv_point = new ImageView(this);
    //        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
    //                ViewGroup.LayoutParams.WRAP_CONTENT,
    //                ViewGroup.LayoutParams.WRAP_CONTENT);
    //        params.setMargins(0, 0,
    //                getResources().getDimensionPixelSize(R.dimen.dp_8),
    //                0);//4个参数按顺序分别是左上右下
    //        iv_point.setLayoutParams(params);
    //        ivPointArray[i] = iv_point;
    //        //第一个页面需要设置为选中状态，这里采用两张不同的图片
    //        if (i == 0) {
    //            iv_point.setBackgroundResource(R.drawable.full_holo);
    //        }
    //        else {
    //            iv_point.setBackgroundResource(R.drawable.empty_holo);
    //        }
    //        //将数组中的ImageView加入到ViewGroup
    //        mGuideLlPoint.addView(ivPointArray[i]);
    //    }
    //}
    //
    //
    ///**
    // * 加载图片ViewPager
    // */
    //private void initViewPager() {
    //    //实例化图片资源
    //    imageIdArray = new int[] { R.drawable.guide1, R.drawable.guide2,
    //            R.drawable.guide3, R.drawable.guide4 };
    //    viewList = new ArrayList<>();
    //    //获取一个Layout参数，设置为全屏
    //    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
    //            LinearLayout.LayoutParams.MATCH_PARENT,
    //            LinearLayout.LayoutParams.MATCH_PARENT);
    //
    //    //循环创建View并加入到集合中
    //    int len = imageIdArray.length;
    //    for (int i = 0; i < len; i++) {
    //        //new ImageView并设置全屏和图片资源
    //        ImageView imageView = new ImageView(this);
    //        imageView.setLayoutParams(params);
    //        imageView.setBackgroundResource(imageIdArray[i]);
    //        //将ImageView加入到集合中
    //        viewList.add(imageView);
    //    }
    //
    //    //View集合初始化好后，设置Adapter
    //    mViewPager.setAdapter(new GuidePageAdapter(viewList));
    //    //设置滑动监听
    //    mViewPager.setOnPageChangeListener(this);
    //}
    //
    //
    //private class GuidePageAdapter extends PagerAdapter {
    //
    //    private List<View> viewList;
    //
    //
    //    public GuidePageAdapter(List<View> viewList) {
    //        this.viewList = viewList;
    //    }
    //
    //
    //    /**
    //     * @return 返回页面的个数
    //     */
    //    @Override public int getCount() {
    //        if (viewList != null) {
    //            return viewList.size();
    //        }
    //        return 0;
    //    }
    //
    //
    //    /**
    //     * 判断对象是否生成界面
    //     */
    //    @Override public boolean isViewFromObject(View view, Object object) {
    //        return view == object;
    //    }
    //
    //
    //    /**
    //     * 初始化position位置的界面
    //     */
    //    @Override
    //    public Object instantiateItem(ViewGroup container, int position) {
    //        container.addView(viewList.get(position));
    //        return viewList.get(position);
    //    }
    //
    //
    //    @Override
    //    public void destroyItem(ViewGroup container, int position, Object object) {
    //        container.removeView(viewList.get(position));
    //    }
    //}
    //
    //
    //@Override
    //public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    //
    //}
    //
    //
    ///**
    // * 滑动后的监听
    // */
    //@Override public void onPageSelected(int position) {
    //    //循环设置当前页的标记图
    //    int length = imageIdArray.length;
    //    for (int i = 0; i < length; i++) {
    //        ivPointArray[position].setBackgroundResource(R.drawable.full_holo);
    //        if (position != i) {
    //            ivPointArray[i].setBackgroundResource(R.drawable.empty_holo);
    //        }
    //    }
    //
    //    //判断是否是最后一页，若是则显示按钮
    //    if (position == imageIdArray.length - 1) {
    //        mBtnGo.setVisibility(View.VISIBLE);
    //        // mGuideLlPoint.setVisibility(View.GONE);
    //    }
    //    else {
    //        mBtnGo.setVisibility(View.GONE);
    //        //  mGuideLlPoint.setVisibility(View.VISIBLE);
    //    }
    //}
    //
    //
    //@Override public void onPageScrollStateChanged(int state) {
    //
    //}
}
