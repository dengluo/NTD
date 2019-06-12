package com.qiangu.ntd.view.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import butterknife.OnClick;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.BaseDialogFragment;

public class PayPasswordDialogFragment extends BaseDialogFragment {

    private String mShareUrl;
    private String mShareTitle;


    public static PayPasswordDialogFragment newInstance(String shareUrl, String shareTitle) {
        PayPasswordDialogFragment fragment = new PayPasswordDialogFragment();
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
        return R.layout.dialog_fragment_pay_password;
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
            attributes.gravity = Gravity.CENTER;   //设置在底部
            attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
            attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(attributes);
        }
    }


    @OnClick(R.id.ibtClose) public void onViewClicked() {
        dismiss();
    }
}
