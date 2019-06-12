package com.qiangu.ntd.ui.user;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.BaseActivity;
import com.qiangu.ntd.base.utils.LoginTimeCount;

public class ForgetPasswordActivity extends BaseActivity {

    @BindView(R.id.etCardNumber) EditText mEtCardNumber;
    @BindView(R.id.etPhone) EditText mEtPhone;
    @BindView(R.id.etVerificationCode) EditText mEtVerificationCode;
    @BindView(R.id.btnSendCode) Button mBtnSendCode;
    @BindView(R.id.etPassword) EditText mEtPassword;
    @BindView(R.id.etConfirmPassword) EditText mEtConfirmPassword;
    @BindView(R.id.btnNext) Button mBtnNext;
    private LoginTimeCount mLoginTimeCount;

    @Override protected void getBundleExtras(Bundle extras) {

    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.activity_forget_password;
    }


    @Override protected View getLoadingTargetView() {
        return null;
    }


    @Override protected void initView(Bundle savedInstanceState) {
        //mEtCardNumber.addTextChangedListener(new EditCodeChangedListener());
        mLoginTimeCount = new LoginTimeCount(60000, 1000, mBtnSendCode,
                mContext);//构造CountDownTimer对象
    }


    @Override protected void initToolbar() {

    }


    @Override protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }



    @OnClick({ R.id.ibtBack, R.id.btnNext,R.id.btnSendCode })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibtBack:
                finish();
                break;
            case R.id.btnNext:
                break;
            case R.id.btnSendCode:
                mLoginTimeCount.start();
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
            //if (mEtCardNumber.length() == 14 &&
            //        mEtPassword.length() == 11  ) {
            //    mBtnLogin.setEnabled(true);
            //}
            //else {
            //    mBtnLogin.setEnabled(false);
            //}
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

    private void sendSms() {
        String phone = mEtPhone.getText().toString().trim();
        //if (TextUtils.isEmpty(phone) || phone.length() < 11) {
        //    ToastUtils.showLongToast(R.string.phone_cannot_be_empty);
        //    return;
        //}
        //DataManager.sendSms(phone, "01")
        //           .compose(bindToLifecycle())
        //           .subscribe(new CObserver<Base>() {
        //               @Override public void onPrepare() {
        //                   mProgressDialogUtils.showProgress(
        //                           R.string.sendSmsIng);
        //               }
        //
        //
        //               @Override public void onError(ErrorThrowable throwable) {
        //                   mProgressDialogUtils.hideProgress();
        //                   ToastUtils.showLongToast(throwable.errorMsg + "");
        //               }
        //
        //
        //               @Override public void onSuccess(Base base) {
        //                   ToastUtils.showLongToast("发送成功,请注意查收!");
        //                   mTvErrorTips.setText("");
        //                   mProgressDialogUtils.hideProgress();
        //                   mLoginTimeCount.start();
        //                   mEtVerificationCode.setEnabled(true);
        //                   //mEtVerificationCode.setText("");
        //                   mEtVerificationCode.requestFocus();//获取焦点 光标出现
        //                   //KeyboardUtils.hideSoftInput(LoginActivity.this);
        //               }
        //           });
    }
}
