package com.qiangu.ntd.view.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.BaseDialogFragment;

public class ConfirmExchangeDialogFragment extends BaseDialogFragment {

    @BindView(R.id.tvTitle) TextView mTvTitle;
    @BindView(R.id.tvContent) TextView mTvContent;
    @BindView(R.id.rlStatus) RelativeLayout mRlStatus;
    @BindView(R.id.llConfirmExchange) LinearLayout mLlConfirmExchange;
    private String mShareUrl;
    private String mShareTitle;


    public static ConfirmExchangeDialogFragment newInstance(String shareUrl, String shareTitle) {
        ConfirmExchangeDialogFragment fragment
                = new ConfirmExchangeDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("shareUrl", shareUrl);
        bundle.putString("shareTitle", shareTitle);
        fragment.setArguments(bundle);
        return fragment;
    }


    private void getExtra() {
        mShareUrl = getArguments().getString("shareUrl");
        mShareTitle = getArguments().getString("shareTitle");
    }


    @Override protected int getContentViewID() {
        return R.layout.dialog_fragment_confirm_exchange;
    }


    @Override protected void initView(View view) {
        getExtra();
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
    }


    @Override public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            //  Dialog dialog = new Dialog(mContext, R.style.BottomDialog);
            //            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //
            dialog.getWindow()
                  .setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCanceledOnTouchOutside(true); // 外部点击取消
            // 设置宽度为屏宽, 靠近屏幕底部。
            final Window window = dialog.getWindow();
            window.setWindowAnimations(R.style.AnimBottom);
            //设置dialog的位置
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = Gravity.BOTTOM;   //设置在底部
            attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
            attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(attributes);
        }
    }


    @OnClick({ R.id.btnConfirm, R.id.btnConfirmExchange,R.id.btnCancel })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnConfirm:
                dismiss();
                break;
            case R.id.btnCancel:
                dismiss();
                break;
            case R.id.btnConfirmExchange:
                mRlStatus.setVisibility(View.VISIBLE);
                mLlConfirmExchange.setVisibility(View.GONE);
                break;
        }
    }
}
