package com.qiangu.ntd.ui.user;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.BaseActivity;
import com.qiangu.ntd.base.utils.ActivityUtils;
import com.qiangu.ntd.base.utils.LoginTimeCount;
import com.qiangu.ntd.view.dialog.AreaCodeDialogFragment;

public class RegisteredInfoActivity extends BaseActivity {

    @BindView(R.id.etPassword) EditText mEtPassword;
    @BindView(R.id.cbProtocol) CheckBox mCbProtocol;
    @BindView(R.id.tvForgetPassword) TextView mTvForgetPassword;
    @BindView(R.id.etPhone) EditText mEtPhone;
    @BindView(R.id.btnSendCode) Button mBtnSendCode;
    @BindView(R.id.btnNext) Button mBtnNext;
    private LoginTimeCount mLoginTimeCount;


    @Override protected void getBundleExtras(Bundle extras) {

    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.activity_registered;
    }


    @Override protected View getLoadingTargetView() {
        return null;
    }


    @Override protected void initView(Bundle savedInstanceState) {
        mEtPhone.addTextChangedListener(new EditCodeChangedListener());
        mEtPassword.addTextChangedListener(new EditCodeChangedListener());
        mLoginTimeCount = new LoginTimeCount(60000, 1000, mBtnSendCode,
                mContext);//构造CountDownTimer对象
    }


    @Override protected void initToolbar() {

    }


    @Override protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }


    @OnClick({ R.id.ibtBack, R.id.cbProtocol,
                     R.id.tvForgetPassword, R.id.btnNext, R.id.btnSendCode,
                     R.id.tvAreaCode }) public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibtBack:
                finish();
                break;
            case R.id.cbProtocol:
                break;
            case R.id.tvForgetPassword:
                ActivityUtils.launchActivity(mContext,
                        ForgetPasswordActivity.class);
                break;

            case R.id.btnNext:
                break;

            case R.id.btnSendCode:
                mLoginTimeCount.start();
                break;
            case R.id.tvAreaCode:
                AreaCodeDialogFragment areaCodeDialogFragment
                        = AreaCodeDialogFragment.newInstance("", "");
                //设置目标Fragment
                areaCodeDialogFragment.show(getSupportFragmentManager(),
                        "areaCodeDialogFragment");
                break;
        }
    }


    private class EditCodeChangedListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }


        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }


        @Override public void afterTextChanged(Editable editable) {
            if (mEtPhone.length() == 14 && mEtPassword.length() == 11) {
                mBtnNext.setEnabled(true);
            }
            else {
                mBtnNext.setEnabled(false);
            }
            //    else {
            //        if (mEtVerificationCode.length() == 6 &&
            //                mEtPhone.length() == 11) {
            //            mBtnLogin.setEnabled(true);
            //        }
            //        else {
            //            mBtnLogin.setEnabled(false);
            //        }
            //    }
            //    if (mEtPhone.length() < 11) {
            //        mBtnSendCode.setEnabled(false);
            //    }
            //
            //    if (mEtPhone.length() == 11) {
            //        if (mBtnSendCode.getText().toString().trim().equals("获取验证码")) {
            //            mEtVerificationCode.setEnabled(false);
            //            mBtnSendCode.setEnabled(true);
            //        }
            //        if (mBtnSendCode.getText().toString().trim().contains("s")) {
            //            mBtnSendCode.setEnabled(false);
            //        }
            //        if (mBtnSendCode.getText().toString().trim().equals("重新获取")) {
            //            mBtnSendCode.setEnabled(true);
            //        }
            //
            //        if (mIsLoginFailure) {
            //            mBtnSendCode.setEnabled(true);
            //            mIsLoginFailure = false;
            //        }
            //    }
        }
    }
}
