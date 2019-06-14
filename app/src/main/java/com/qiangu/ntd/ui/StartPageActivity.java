package com.qiangu.ntd.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.fragment.app.FragmentActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.f2prateek.rx.preferences2.RxSharedPreferences;
import com.qiangu.ntd.R;
import com.qiangu.ntd.app.App;
import com.qiangu.ntd.app.AppContext;
import com.qiangu.ntd.app.Constant;
import com.qiangu.ntd.base.utils.ACache;
import com.qiangu.ntd.base.utils.ActivityUtils;
import com.qiangu.ntd.base.utils.AppUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * 注意，我们这里让WelcomeActivity继承Activity不要继承AppCompatActivity，因为AppCompatActivity会默认去加载主题，造成卡顿
 */
public class StartPageActivity extends FragmentActivity {
    @BindView(R.id.iv_entry) ImageView mIVEntry;
    private ACache mACache;
    //注意mACache初始化的位置


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        ButterKnife.bind(this);

        AppContext.STATUS_HEIGHT = AppUtils.getStatusBarHeight(this);
        //AppStatusManager.getInstance()
        //                .setAppStatus(
        //                        AppStatusConstant.STATUS_NORMAL);//进入应用初始化设置成未登录状态
        detectPermission();
    }


    @Override protected void onResume() {
        super.onResume();
        //MobclickAgent.onResume(this);
        //Glide.with(this).resumeRequests();
    }


    @Override protected void onPause() {
        super.onPause();
        //MobclickAgent.onPause(this);
        // Glide.with(this).pauseRequests();
    }


    private void startPage() {
        float nowVersionCode = AppUtils.getAppVersionCode(
                StartPageActivity.this);
        float spVersionCode = RxSharedPreferences.create(
                getDefaultSharedPreferences(this))
                                                 .getFloat(
                                                         Constant.KEY_VERSION_CODE)
                                                 .get();

        //if (nowVersionCode > spVersionCode) {
        //    //RxSharedPreferences.create(getDefaultSharedPreferences(this))
        //    //                   .getFloat(Constant.KEY_VERSION_CODE)
        //    //                   .set(nowVersionCode);
        //    //首次启动
        //    ActivityUtils.launchActivity(StartPageActivity.this,
        //            WelcomeGuideActivity.class);
        //}
        //else {
        //    //非首次启动
        //    ActivityUtils.launchActivity(StartPageActivity.this,
        //            MainActivity.class);
        //}
        ActivityUtils.launchActivity(StartPageActivity.this,
                     MainActivity.class);
        finish();
    }


    //Manifest.permission.REQUEST_INSTALL_PACKAGES
    private void detectPermission() {
        new RxPermissions(this).request(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                               .subscribe(granted -> {
                                   if (granted) {// 在android 6.0之前会默认返回true 已经获取权限
                                       runOnUiThread(() -> {
                                           mACache = App.getACache();
                                           AppContext.initAppParams(
                                                   StartPageActivity.this);
                                           startPage();
                                       });
                                   }
                                   else {
                                       // 未获取权限
                                       finish();
                                   }
                               });
    }


    //只显示一次启动页（ App 没被 kill 的情况下）
    @Override public void onBackPressed() {
        // super.onBackPressed(); 	不要调用父类的方法
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
