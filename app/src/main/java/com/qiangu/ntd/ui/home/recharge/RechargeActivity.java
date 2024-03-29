package com.qiangu.ntd.ui.home.recharge;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.jaeger.library.StatusBarUtil;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.BaseActivity;
import com.qiangu.ntd.base.utils.ActivityUtils;
import com.qiangu.ntd.base.utils.StringUtils;
import com.qiangu.ntd.base.utils.ToastUtils;
import com.qiangu.ntd.dao.CObserver;
import com.qiangu.ntd.dao.retrofit.ErrorThrowable;
import com.qiangu.ntd.manager.DataManager;
import com.qiangu.ntd.manager.UserManager;
import com.qiangu.ntd.model.response.Base;
import com.qiangu.ntd.model.response.Recharge;

public class RechargeActivity extends BaseActivity {
    @BindView(R.id.btnRedemptionRecord)
    Button mBtnRedemptionRecord;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.ivQrCode)
    ImageView mIvQrCode;
    @BindView(R.id.tvAddress)
    TextView mTvAddress;
    @BindView(R.id.btnCopyAddress)
    Button mBtnCopyAddress;


    @Override
    protected void getBundleExtras(Bundle extras) {

    }


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_recharge;
    }


    @Override
    protected View getLoadingTargetView() {
        return null;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        StatusBarUtil.setTranslucentForImageViewInFragment(
                RechargeActivity.this, 0,
                null);
    }


    @Override
    protected void initToolbar() {

    }


    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }


    @OnClick({R.id.ibtBack, R.id.btnRedemptionRecord, R.id.btnCopyAddress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibtBack:
                finish();
                break;
            case R.id.btnRedemptionRecord:
                ActivityUtils.launchActivity(mContext,
                        RechargeRecordActivity.class);
                break;
            case R.id.btnCopyAddress:
                if (!StringUtils.empty(mTvAddress.getText().toString())) {
                    //获取剪贴板管理器：
                    ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    // 创建普通字符型ClipData
                    ClipData mClipData = ClipData.newPlainText("Label", mTvAddress.getText().toString());
                    // 将ClipData内容放到系统剪贴板里。
                    cm.setPrimaryClip(mClipData);
                    ToastUtils.showShortToast(getString(R.string.yifuzhi));
                } else {
                    ToastUtils.showShortToast(getString(R.string.dizhiweikong));
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        getBbAddress();
    }

    private void getBbAddress() {
        DataManager.getInstance()
                .getBbAddress(UserManager.getInstance().getUser().token)
                .compose(bindToLifecycle())
                .
                        subscribe(new CObserver<Recharge>() {
                            @Override
                            public void onPrepare() {
                            }


                            @Override
                            public void onError(ErrorThrowable throwable) {
                                mProgressDialogUtils.hideProgress();
                                ToastUtils.showLongToast(throwable.msg);

                            }


                            @Override
                            public void onSuccess(Recharge recharge) {
                                mProgressDialogUtils.hideProgress();
                                mTvAddress.setText(recharge.bbAddress);

                            }
                        });
    }
}
