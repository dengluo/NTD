package com.qiangu.ntd.ui.user;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
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
import com.qiangu.ntd.base.utils.LoginTimeCount;
import com.qiangu.ntd.base.utils.ToastUtils;
import com.qiangu.ntd.dao.CObserver;
import com.qiangu.ntd.dao.retrofit.ErrorThrowable;
import com.qiangu.ntd.manager.DataManager;
import com.qiangu.ntd.model.response.Base;
import com.qiangu.ntd.view.dialog.AreaCodeDialogFragment;

public class RegisteredActivity extends BaseActivity {

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
        StatusBarUtil.setTranslucentForImageViewInFragment(
                RegisteredActivity.this, 0, null);
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


    @OnClick({ R.id.ibtBack, R.id.cbProtocol, R.id.tvForgetPassword,
                     R.id.btnNext, R.id.btnSendCode, R.id.tvAreaCode })
    public void onViewClicked(View view) {
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

                sendSms();
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


    private void sendSms() {
        String phone = mEtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone) || phone.length() < 11) {
            ToastUtils.showLongToast(R.string.phone_cannot_be_empty);
            return;
        }
        DataManager.getVerifyCode(phone)
                   .compose(bindToLifecycle())
                   .subscribe(new CObserver<Base>() {
                       @Override public void onPrepare() {
                           mProgressDialogUtils.showProgress(
                                   R.string.sendSmsIng);
                       }


                       @Override public void onError(ErrorThrowable throwable) {
                           Log.d("result","阿斯顿");
                           mProgressDialogUtils.hideProgress();
                           ToastUtils.showLongToast(throwable.msg + "");
                       }


                       @Override public void onSuccess(Base base) {
                           ToastUtils.showLongToast("发送成功,请注意查收!");
                           mProgressDialogUtils.hideProgress();
                           mLoginTimeCount.start();
                           mEtPassword.setEnabled(true);
                           //mEtVerificationCode.setText("");
                           mEtPassword.requestFocus();//获取焦点 光标出现
                           //KeyboardUtils.hideSoftInput(LoginActivity.this);
                       }
                   });
    }
}
