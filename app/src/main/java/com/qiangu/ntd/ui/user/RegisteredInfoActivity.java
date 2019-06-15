package com.qiangu.ntd.ui.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.BaseActivity;
import com.qiangu.ntd.base.utils.ToastUtils;
import com.qiangu.ntd.dao.CObserver;
import com.qiangu.ntd.dao.retrofit.ErrorThrowable;
import com.qiangu.ntd.manager.DataManager;
import com.qiangu.ntd.model.response.Base;

public class RegisteredInfoActivity extends BaseActivity {

    @BindView(R.id.etCardNumber) EditText mEtCardNumber;
    @BindView(R.id.etPassword) EditText mEtPassword;
    @BindView(R.id.etConfirmPassword) EditText mEtConfirmPassword;
    private String mTel;


    public static Bundle buildBundle(String tel) {
        Bundle bundle = new Bundle();
        bundle.putString("tel", tel);
        return bundle;
    }


    @Override protected void getBundleExtras(Bundle extras) {
        mTel = extras.getString("tel");
    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.activity_registered_info;
    }


    @Override protected View getLoadingTargetView() {
        return null;
    }


    @Override protected void initView(Bundle savedInstanceState) {
        StatusBarUtil.setTranslucentForImageViewInFragment(
                RegisteredInfoActivity.this, 0, null);
    }


    @Override protected void initToolbar() {

    }


    @Override protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }


    @OnClick({ R.id.ibtBack, R.id.btnRegistered })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibtBack:
                finish();
                break;
            case R.id.btnRegistered:
                registUser();
                break;
        }
    }


    private void registUser() {
        String cardNumber = mEtCardNumber.getText().toString().trim();
        if (TextUtils.isEmpty(cardNumber)) {
            ToastUtils.showLongToast(R.string.card_number_cannot_be_empty);
            return;
        }
        String password = mEtPassword.getText().toString().trim();
        String confirmPassword = mEtConfirmPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            ToastUtils.showLongToast(R.string.password_cannot_be_empty);
            return;
        }
        if (TextUtils.isEmpty(confirmPassword) ||
                confirmPassword.length() < 6) {
            ToastUtils.showLongToast(R.string.confirm_cannot_be_empty);
            return;
        }

        DataManager.registUser(mTel, cardNumber, password, confirmPassword)
                   .compose(bindToLifecycle())
                   .subscribe(new CObserver<Base>() {
                       @Override public void onPrepare() {
                           mProgressDialogUtils.showProgress(
                                   R.string.registerIng);
                       }


                       @Override public void onError(ErrorThrowable throwable) {
                           mProgressDialogUtils.hideProgress();
                           ToastUtils.showLongToast(throwable.msg + "");
                       }


                       @Override public void onSuccess(Base base) {
                           mProgressDialogUtils.hideProgress();
                           ToastUtils.showLongToast(R.string.registerSuccess);
                           finish();
                       }
                   });
    }


}
