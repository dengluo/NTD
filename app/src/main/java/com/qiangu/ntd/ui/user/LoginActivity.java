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

public class LoginActivity extends BaseActivity {

    @BindView(R.id.etCardNumber) EditText mEtCardNumber;
    @BindView(R.id.etPassword) EditText mEtPassword;
    @BindView(R.id.btnLogin) Button mBtnLogin;
    @BindView(R.id.tvRegistered) TextView mTvRegistered;
    @BindView(R.id.cbProtocol) CheckBox mCbProtocol;
    @BindView(R.id.tvForgetPassword) TextView mTvForgetPassword;


    @Override protected void getBundleExtras(Bundle extras) {

    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.activity_login;
    }


    @Override protected View getLoadingTargetView() {
        return null;
    }


    @Override protected void initView(Bundle savedInstanceState) {
        mEtCardNumber.addTextChangedListener(new EditCodeChangedListener());
        mEtPassword.addTextChangedListener(new EditCodeChangedListener());
    }


    @Override protected void initToolbar() {

    }


    @Override protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }


    @OnClick({ R.id.ibtBack, R.id.btnLogin, R.id.tvRegistered, R.id.cbProtocol,
                     R.id.tvForgetPassword })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibtBack:
                finish();
                break;
            case R.id.btnLogin:
                break;
            case R.id.tvRegistered:
                ActivityUtils.launchActivity(mContext, RegisteredActivity.class);
                break;
            case R.id.cbProtocol:
                break;
            case R.id.tvForgetPassword:
                ActivityUtils.launchActivity(mContext,
                        ForgetPasswordActivity.class);
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
            if (mEtCardNumber.length() == 14 && mEtPassword.length() == 11) {
                mBtnLogin.setEnabled(true);
            }
            else {
                mBtnLogin.setEnabled(false);
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
