package com.qiangu.ntd.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import com.qiangu.ntd.R;
import com.qiangu.ntd.app.AppContext;
import com.qiangu.ntd.base.BaseActivity;
import com.qiangu.ntd.base.utils.ToastUtils;
import com.qiangu.ntd.model.event.LoginEvent;
import com.qiangu.ntd.view.widget.BrowserLayout;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2016/12/27.
 */

public class WebActivity extends BaseActivity {
    public @BindView(R.id.bl) BrowserLayout mBrowserLayout;
    private String mWebUrl;


    //public static Bundle buildBundle(String url, String refresh) {
    //    Bundle bundle = new Bundle();
    //    bundle.putString("url", url);
    //    bundle.putString("refresh", refresh);
    //    return bundle;
    //}
    public static Bundle buildBundle(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        return bundle;
    }


    @Override protected void getBundleExtras(Bundle extras) {
        mWebUrl = extras.getString("url");
    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.activity_web;
    }


    @Override protected View getLoadingTargetView() {
        //return ButterKnife.findById(WebActivity.this, R.id.llWeb);
        return null;
    }


    //是否显示error布局
    private void showErrorLayout() {
        if (AppContext.isNetworkAvailable()) {
            toggleShowError(false, "", v -> {
            });
        }
    }


    @Override protected void initView(Bundle savedInstanceState) {
        if (!AppContext.isNetworkAvailable()) {
            toggleShowError(true, "", v -> showErrorLayout());
        }
        getData();
    }


    @Override protected void initToolbar() {

    }


    @Override protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }


    private void getData() {
        if (!TextUtils.isEmpty(mWebUrl)) {
            runOnUiThread(() -> mBrowserLayout.loadUrl(mWebUrl));
        }
        else {
            ToastUtils.showLongToast("获取URL地址失败");
        }
    }


    @Override public void onStart() {
        super.onStart();
        // EventBus.getDefault().register(this);
        //if (mStatusPop.equals("financial")){
        //    EventBus.getDefault().post(new LoginEvent());
        //}

    }


    @Override protected void onStop() {
        //EventBus.getDefault().unregister(this);
        super.onStop();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent resultEvent) {

    }

    @OnClick({ R.id.ibtBack }) public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibtBack:
                finish();
                break;
        }
    }

}
