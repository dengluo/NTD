package com.qiangu.ntd.ui.home.exchange;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.BaseFragment;
import com.qiangu.ntd.base.utils.ActivityUtils;
import com.qiangu.ntd.ui.exchange.ExchangeRecordActivity;
import com.qiangu.ntd.view.dialog.ConfirmExchangeDialogFragment;
import com.qiangu.ntd.view.widget.DecimalEditText;

/**
 * Created by Administrator on 2016/11/24.
 */

public class ExchangeFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.etMoney) DecimalEditText mEtMoney;
    @BindView(R.id.btnConfirmExchange) Button mBtnConfirmExchange;


    public static ExchangeFragment newInstance() {
        ExchangeFragment fragment = new ExchangeFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override protected int getContentViewID() {
        return R.layout.fragment_exchange;
    }


    @Override protected View getLoadingTargetView() {
        //return ButterKnife.findById(getActivity(), android.R.id.content);
        return null;
    }


    @Override protected void initView(View view) {
        mEtMoney.addTextChangedListener(new EditCodeChangedListener());
    }


    @OnClick({ R.id.ibtBack, R.id.btnExchangeRecord, R.id.btnConfirmExchange })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibtBack:
                break;
            case R.id.btnExchangeRecord:
                ActivityUtils.launchActivity(mContext,
                        ExchangeRecordActivity.class);
                break;

            case R.id.btnConfirmExchange:
                ConfirmExchangeDialogFragment confirmExchangeDialogFragment
                        = ConfirmExchangeDialogFragment.newInstance("", "");
                //设置目标Fragment
                confirmExchangeDialogFragment.show(getFragmentManager(),
                        "confirmExchangeDialogFragment");
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
            if (mEtMoney.length() > 0) {
                mBtnConfirmExchange.setEnabled(true);
            }
            else {
                mBtnConfirmExchange.setEnabled(false);
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
