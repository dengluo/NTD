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
import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.ui.activity.MediaActivity;
import cn.finalteam.rxgalleryfinal.ui.base.IRadioImageCheckedListener;
import cn.finalteam.rxgalleryfinal.utils.Logger;
import cn.finalteam.rxgalleryfinal.utils.PermissionCheckUtils;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.BaseDialogFragment;

public class SelectPicWayDialogFragment extends BaseDialogFragment {

    private String mShareUrl;
    private String mShareTitle;


    public static SelectPicWayDialogFragment newInstance(String shareUrl, String shareTitle) {
        SelectPicWayDialogFragment fragment = new SelectPicWayDialogFragment();
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
        return R.layout.dialog_fragment_select_pic_way;
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


    @OnClick({ R.id.tvTakePhoto, R.id.tvAlbum, R.id.tvCancel })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvTakePhoto:
                if (PermissionCheckUtils.checkCameraPermission(getActivity(),
                        "", MediaActivity.REQUEST_CAMERA_ACCESS_PERMISSION)) {
                    RxGalleryFinalApi.openZKCamera(getActivity());
                }
                break;
            case R.id.tvAlbum:

                RxGalleryFinalApi.getInstance(getActivity())
                                 .onCrop(true)//是否裁剪
                                 .openGalleryRadioImgDefault(
                                         new RxBusResultDisposable() {
                                             @Override
                                             protected void onEvent(Object o)
                                                     throws Exception {
                                                 //Logger.i("只要选择图片就会触发");
                                             }
                                         })
                                 .onCropImageResult(
                                         new IRadioImageCheckedListener() {
                                             @Override
                                             public void cropAfter(Object t) {

                                             }


                                             @Override
                                             public boolean isActivityFinish() {
                                                 Logger.i(
                                                         "返回false不关闭，返回true则为关闭");
                                                 return true;
                                             }
                                         });
                break;
            case R.id.tvCancel:
                break;
        }
        dismiss();
    }
}
