package com.qiangu.ntd.ui.home.transfer;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.BaseActivity;
import com.qiangu.ntd.base.utils.ActivityUtils;
import com.qiangu.ntd.view.dialog.PayPasswordDialogFragment;
import com.qiangu.ntd.view.widget.DecimalEditText;

public class TransferActivity extends BaseActivity {

    @BindView(R.id.etCollectionName) EditText mEtCollectionName;
    @BindView(R.id.etAccountsReceivable) EditText mEtAccountsReceivable;
    @BindView(R.id.etMoney) DecimalEditText mEtMoney;


    @Override protected void getBundleExtras(Bundle extras) {

    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.activity_transfer;
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

    @OnClick({ R.id.ibtBack, R.id.btnTransferRecord, R.id.btnNext })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibtBack:
                finish();
                break;
            case R.id.btnTransferRecord:
                ActivityUtils.launchActivity(mContext,
                        TransferRecordActivity.class);
                break;
            case R.id.btnNext:
                PayPasswordDialogFragment payPasswordDialogFragment
                        = PayPasswordDialogFragment.newInstance("","");
                //设置目标Fragment
                payPasswordDialogFragment.show(getSupportFragmentManager(),
                        "payPasswordDialogFragment");
                break;
        }
    }
}
