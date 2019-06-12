package com.qiangu.ntd.ui.financial;

import android.os.Bundle;
import android.view.View;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.BaseActivity;

public class FinancialActivity extends BaseActivity {


    public FinancialFragment mFinancialFragment;
    @Override protected void getBundleExtras(Bundle extras) {

    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.activity_exchange;
    }


    @Override protected View getLoadingTargetView() {
        return null;
    }


    @Override protected void initView(Bundle savedInstanceState) {
    mFinancialFragment = FinancialFragment.newInstance();
        loadMultipleRootFragment(R.id.fragment_container, 0,
                mFinancialFragment);
    }


    @Override protected void initToolbar() {

    }


    @Override protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }



}
