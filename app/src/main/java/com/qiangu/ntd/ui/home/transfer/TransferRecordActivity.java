package com.qiangu.ntd.ui.home.transfer;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.BaseActivity;

public class TransferRecordActivity extends BaseActivity {
    @BindView(R.id.ibtBack) ImageButton mIbtBack;
    @BindView(R.id.llDetails) LinearLayout mLlDetails;
    @BindView(R.id.tvBalance) TextView mTvBalance;
    @BindView(R.id.rv) RecyclerView mRv;
    @BindView(R.id.llBalance) LinearLayout mLlBalance;


    @Override protected void getBundleExtras(Bundle extras) {

    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.activity_transfer_record;
    }


    @Override protected View getLoadingTargetView() {
        return null;
    }


    @Override protected void initView(Bundle savedInstanceState) {
        StatusBarUtil.setTranslucentForImageViewInFragment(
                TransferRecordActivity.this, 0,
                null);
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
