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
import com.qiangu.ntd.base.utils.ToastUtils;
import com.qiangu.ntd.dao.CObserver;
import com.qiangu.ntd.dao.retrofit.ErrorThrowable;
import com.qiangu.ntd.manager.DataManager;
import com.qiangu.ntd.manager.UserManager;
import com.qiangu.ntd.model.response.Base;

public class QuitDialogFragment extends BaseDialogFragment {

    private String mShareUrl;
    private String mShareTitle;


    public static QuitDialogFragment newInstance(String shareUrl, String shareTitle) {
        QuitDialogFragment fragment = new QuitDialogFragment();
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
        return R.layout.dialog_fragment_quit;
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


    @OnClick({ R.id.btnCancel, R.id.btnConfirm })
    public void onViewClicked(View view) {
        dismiss();
        switch (view.getId()) {
            case R.id.btnCancel:
                break;
            case R.id.btnConfirm:
                loginOut();
                break;
        }
    }


    private void loginOut() {
        DataManager.getInstance()
                   .loginOut(UserManager.getInstance().getUser().token)
                   .compose(bindToLifecycle())
                   .
                           subscribe(new CObserver<Base>() {
                               @Override public void onPrepare() {
                                   mProgressDialogUtils.showProgress(
                                           R.string.loginOutIng);
                               }


                               @Override
                               public void onError(ErrorThrowable throwable) {
                                   mProgressDialogUtils.hideProgress();
                                   ToastUtils.showLongToast(throwable.msg);

                               }


                               @Override public void onSuccess(Base base) {
                                   mProgressDialogUtils.hideProgress();
                                   ToastUtils.showLongToast(
                                           R.string.loginOutSuccess);

                               }
                           });
    }
}
