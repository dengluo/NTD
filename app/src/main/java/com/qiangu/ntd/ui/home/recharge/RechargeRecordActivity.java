package com.qiangu.ntd.ui.home.recharge;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.BaseActivity;

public class RechargeRecordActivity extends BaseActivity {
    @BindView(R.id.ibtBack) ImageButton mIbtBack;
    @BindView(R.id.llDetails) LinearLayout mLlDetails;
    @BindView(R.id.tvBalance) TextView mTvBalance;
    @BindView(R.id.rv) RecyclerView mRv;
    @BindView(R.id.llBalance) LinearLayout mLlBalance;


    @Override protected void getBundleExtras(Bundle extras) {

    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.activity_recharge_record;
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


    @OnClick({ R.id.ibtBack }) public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibtBack:
                finish();
                break;
        }
    }

}
