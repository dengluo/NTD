package com.qiangu.ntd.ui.user;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.SwitchCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import com.qiangu.ntd.R;
import com.qiangu.ntd.base.BaseActivity;
import com.qiangu.ntd.base.utils.ActivityUtils;
import com.qiangu.ntd.view.dialog.QuitDialogFragment;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.scNotice) SwitchCompat mScNotice;


    @Override protected void getBundleExtras(Bundle extras) {

    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.activity_setting;
    }


    @Override protected View getLoadingTargetView() {
        return null;
    }


    @Override protected void initView(Bundle savedInstanceState) {
        StatusBarUtil.setTranslucentForImageViewInFragment(
                SettingActivity.this, 0,
                null);
    }


    @Override protected void initToolbar() {

    }


    @Override protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }


    @OnClick({ R.id.ibtBack, R.id.tvChangePassword, R.id.tvSetPayPassword,
                     R.id.btnExit }) public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibtBack:
                finish();
                break;
            case R.id.tvChangePassword:
                ActivityUtils.launchActivity(mContext, ChangePasswordActivity.class);
                break;
            case R.id.tvSetPayPassword:
                break;
            case R.id.btnExit:
                QuitDialogFragment quitDialogFragment
                        = QuitDialogFragment.newInstance("","");
                //设置目标Fragment
                quitDialogFragment.show(getSupportFragmentManager(),
                        "quitDialogFragment");
                break;
        }
    }
}
