package com.qiangu.ntd.view.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.BaseDialogFragment;
import com.qiangu.ntd.view.adapter.AreaCodeAdapter;
import java.util.ArrayList;
import java.util.List;

public class AreaCodeDialogFragment extends BaseDialogFragment {


    private String mShareUrl;
    private String mShareTitle;
    private AreaCodeAdapter mAreaCodeAdapter;
    @BindView(R.id.rv) RecyclerView mRv;
    private List<String> mList;


    public static AreaCodeDialogFragment newInstance(String shareUrl, String shareTitle) {
        AreaCodeDialogFragment fragment = new AreaCodeDialogFragment();
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
        return R.layout.dialog_fragment_area_code;
    }


    @Override protected void initView(View view) {
        getExtra();
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        mList = new ArrayList<>();
        mList.add("学好Java、Android、C#、");
        mList.add("走遍天下都不怕！！！！！");
        mList.add("不是我吹，就怕你做不到，哈哈");
        mAreaCodeAdapter = new AreaCodeAdapter();
        mAreaCodeAdapter.openLoadAnimation();
        mRv.setAdapter(mAreaCodeAdapter);
        //mEmptyViewGroud = getLayoutInflater().inflate(
        //        R.layout.layout_empty_view, (ViewGroup) mRv.getParent(), false);
        //TextView tvEmptyContent = (TextView) mEmptyViewGroud.findViewById(
        //        R.id.tvEmptyContent);
        //tvEmptyContent.setText("暂无通知");
        //Drawable drawable = getResources().getDrawable(
        //        R.drawable.bg_empty_collection);
        //drawable.setBounds(0, 0, drawable.getMinimumWidth(),
        //        drawable.getMinimumHeight());
        //tvEmptyContent.setCompoundDrawables(null, drawable, null, null);
        mRv.setLayoutManager(new LinearLayoutManager(mContext));
        mAreaCodeAdapter.setNewData(mList);
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



}
