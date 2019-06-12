package com.qiangu.ntd.ui.exchange;

import android.os.Bundle;
import android.view.View;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.BaseActivity;
import com.qiangu.ntd.ui.home.exchange.ExchangeFragment;

public class ExchangeActivity extends BaseActivity {


    public ExchangeFragment mExchangeFragment;
    @Override protected void getBundleExtras(Bundle extras) {

    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.activity_exchange;
    }


    @Override protected View getLoadingTargetView() {
        return null;
    }


    @Override protected void initView(Bundle savedInstanceState) {
        mExchangeFragment = ExchangeFragment.newInstance();
        loadMultipleRootFragment(R.id.fragment_container, 0,
                mExchangeFragment);
    }


    @Override protected void initToolbar() {

    }


    @Override protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }



}
