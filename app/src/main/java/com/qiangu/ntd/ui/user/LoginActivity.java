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
import com.jaeger.library.StatusBarUtil;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.BaseActivity;
import com.qiangu.ntd.base.utils.ActivityUtils;
import com.qiangu.ntd.base.utils.ToastUtils;
import com.qiangu.ntd.dao.retrofit.ErrorThrowable;
import com.qiangu.ntd.model.event.LoginEvent;
import com.qiangu.ntd.model.response.User;
import org.greenrobot.eventbus.EventBus;

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
        StatusBarUtil.setTranslucentForImageViewInFragment(LoginActivity.this,
                0, null);
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
                login();
                break;
            case R.id.tvRegistered:
                ActivityUtils.launchActivity(mContext,
                        RegisteredActivity.class);
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


    private void login() {
        //String phone = mEtPhone.getText().toString().trim();
        //if (TextUtils.isEmpty(phone) || phone.length() < 11) {
        //    ToastUtils.showLongToast(R.string.phone_cannot_be_empty);
        //    return;
        //}
        //if (TextUtils.isEmpty(verificationCode)) {
        //    ToastUtils.showLongToast(
        //            R.string.verification_code_cannot_be_empty);
        //    return;
        //}
        //
        //UserManager.getInstance()
        //           .login(phone)
        //           .compose(bindToLifecycle())
        //           .
        //                   subscribe(new CObserver<User>() {
        //                       @Override public void onPrepare() {
        //                           mProgressDialogUtils.showProgress(
        //                                   R.string.loginIng);
        //                       }
        //
        //
        //                       @Override
        //                       public void onError(ErrorThrowable throwable) {
        //                           loginFailure(throwable);
        //                       }
        //
        //
        //                       @Override public void onSuccess(User user) {
        //                           //setResult(RESULT_OK,
        //                           //        new Intent().putExtra("user", user));
        //                           loginSuccess(user);
        //                       }
        //                   });
    }


    private void loginSuccess(User user) {
        //mIsLoginSuccess = true;
        mProgressDialogUtils.hideProgress();
        //runOnUiThread(() -> {
        //    ToastUtils.showLongToast(R.string.loginSuccess);
        //});
        EventBus.getDefault().post(new LoginEvent());
        finish();
    }


    @Override public void finish() {
        //setResult(RESULT_OK,
        //        new Intent().putExtra("isLoginSuccess", mIsLoginSuccess));
        super.finish();
    }


    private void loginFailure(ErrorThrowable throwable) {

        //setResult(RESULT_OK,
        //        new Intent().putExtra("user", new Base(-1, msg, "")));
        mProgressDialogUtils.hideProgress();
        ToastUtils.showLongToast(throwable.errorMsg);
    }
}
