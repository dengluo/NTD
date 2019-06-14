package com.qiangu.ntd.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.BindView;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.ui.base.IRadioImageCheckedListener;
import cn.finalteam.rxgalleryfinal.utils.Logger;
import com.jaeger.library.StatusBarUtil;
import com.qiangu.ntd.R;
import com.qiangu.ntd.app.App;
import com.qiangu.ntd.app.AppContext;
import com.qiangu.ntd.app.AppStatusConstant;
import com.qiangu.ntd.app.Constant;
import com.qiangu.ntd.base.BaseActivity;
import com.qiangu.ntd.base.BaseFragment;
import com.qiangu.ntd.base.utils.ActivityUtils;
import com.qiangu.ntd.base.utils.ToastUtils;
import com.qiangu.ntd.model.event.LoginEvent;
import com.qiangu.ntd.ui.financial.FinancialFragment;
import com.qiangu.ntd.ui.home.HomeFragment;
import com.qiangu.ntd.ui.home.exchange.ExchangeFragment;
import com.qiangu.ntd.ui.user.AboutActivity;
import com.qiangu.ntd.ui.user.FeedbackActivity;
import com.qiangu.ntd.ui.user.LoginActivity;
import com.qiangu.ntd.ui.user.SettingActivity;
import com.qiangu.ntd.view.dialog.SelectPicWayDialogFragment;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity {
    private static long DOUBLE_CLICK_TIME = 0L;
    public @BindView(R.id.btn_home) Button mBtnHome;
    @BindView(R.id.btn_financial) Button mBtnFinancial;
    @BindView(R.id.btn_exchange) Button mBtnExchange;
    public HomeFragment mHomeFragment;
    public ExchangeFragment mExchangeFragment;
    public FinancialFragment mFinancialFragment;
    @BindView(R.id.ivUserHead) ImageView mIvUserHead;
    @BindView(R.id.tvLogin) TextView mTvLogin;
    private Button[] mTabs;
    private int index;
    private int currentTabIndex;
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    private BaseFragment[] mFragments = new BaseFragment[3];
    //public boolean isLoadingFinancialFragment = false;
    public boolean mIsFirstClicked = true;

    // public @BindView(R.id.tvToolbarTitle) TextView mTvToolbarTitle;
    //@BindView(R.id.ivLeft) ImageView mIvLeft;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.llLeft) RelativeLayout mLlLeft;


    @Override protected void getBundleExtras(Bundle extras) {

    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }


    @Override protected View getLoadingTargetView() {
        // return ButterKnife.findById(MainActivity.this, R.id.rlMain);
        return null;
    }


    @Override protected void initView(Bundle savedInstanceState) {
        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, 0,
                null);
        mTabs = new Button[3];
        mTabs[0] = mBtnHome;
        mTabs[1] = mBtnExchange;
        mTabs[2] = mBtnFinancial;
        // select first tab
        mTabs[0].setSelected(true);
        mHomeFragment = HomeFragment.newInstance();
        mExchangeFragment = ExchangeFragment.newInstance();
        mFinancialFragment = FinancialFragment.newInstance();
        if (savedInstanceState == null) {
            mFragments[FIRST] = mHomeFragment;
            mFragments[SECOND] = mExchangeFragment;
            mFragments[THIRD] = mFinancialFragment;
            loadMultipleRootFragment(R.id.fragment_container, FIRST,
                    mFragments[FIRST], mFragments[SECOND], mFragments[THIRD]);
        }
        else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用,也可以通过getSupportFragmentManager.getFragments()自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = findFragment(HomeFragment.class);
            mFragments[SECOND] = findFragment(ExchangeFragment.class);
            mFragments[THIRD] = findFragment(FinancialFragment.class);
        }
        new Handler().postDelayed(
                () -> runOnUiThread(() -> resetFragmentView(mFragments[FIRST])),
                1);
        //checkForUpdates();
        //
        //float nowVersionCode = AppUtils.getAppVersionCode(MainActivity.this);
        //float spVersionCode = RxSharedPreferences.create(
        //        getDefaultSharedPreferences(this))
        //                                         .getFloat(
        //                                                 Constant.KEY_VERSION_CODE)
        //                                         .get();
        //if (nowVersionCode > spVersionCode) {
        //    RxSharedPreferences.create(getDefaultSharedPreferences(this))
        //                       .getFloat(Constant.KEY_VERSION_CODE)
        //                       .set(nowVersionCode);
        //    AdvertisingDialogFragment advertisingDialogFragment
        //            = AdvertisingDialogFragment.newInstance();
        //    advertisingDialogFragment.show(getSupportFragmentManager(),
        //            "advertisingDialogFragment");
        //    //mRlGuideTips.setVisibility(View.VISIBLE);
        //}
        //else {
        //
        //}
    }


    //左边菜单开关事件
    public void openLeftLayout() {
        if (mDrawerLayout.isDrawerOpen(mLlLeft)) {
            mDrawerLayout.closeDrawer(mLlLeft);
        }
        else {
            mDrawerLayout.openDrawer(mLlLeft);
        }
    }


    @Override protected void initToolbar() {

    }


    @Override protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }


    /**
     * on tab clicked
     */
    public void onTabClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_home:
                index = FIRST;
                break;
            case R.id.btn_exchange:
                index = SECOND;
                break;
            case R.id.btn_financial:
                index = THIRD;
                break;
        }

        if (currentTabIndex != index) {
            resetFragmentView(mFragments[index]);
            showHideFragment(mFragments[index], mFragments[currentTabIndex]);
        }
        mTabs[currentTabIndex].setSelected(false);
        // set current tab selected
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }


    @Override public void onBackPressedSupport() {
        super.onBackPressedSupport();
    }


    public void resetFragmentView(BaseFragment fragment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View contentView = findViewById(android.R.id.content);
            if (contentView != null) {
                ViewGroup rootView;
                rootView = (ViewGroup) ((ViewGroup) contentView).getChildAt(0);
                if (rootView.getPaddingTop() != 0) {
                    rootView.setPadding(0, 0, 0, 0);
                }
            }
            if (fragment.getView() != null) {
                fragment.getView()
                        .setLayoutParams(new FrameLayout.LayoutParams(
                                FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.MATCH_PARENT));
                fragment.getView()
                        .setPadding(0, AppContext.STATUS_HEIGHT, 0, 0);
            }
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshEvent(LoginEvent resultEvent) {

    }


    @Override protected void onStart() {
        super.onStart();
        // EventBus.getDefault().register(this);
        //if (!isCurrentRunningForeground) {
        //    Log.d("result","onStart()"+App.getACache().getAsString(Constant.KEY_GESTURE_PASSWORD_VERIFY));
        //    Log.d("result", ">>>>>>>>>>>>>>>>>>>切到前台 activity process");
        //
        //}
        Log.d("result", " onStart()");
    }


    @Override protected void onStop() {
        //EventBus.getDefault().unregister(this);
        //isCurrentRunningForeground = AppUtils.isRunningForeground(mContext);
        //if (!isCurrentRunningForeground) {
        //    Log.d("result", ">>>>>>>>>>>>>>>>>>>切到后台 activity process");
        //}
        super.onStop();
    }


    @Override protected void onDestroy() {
        //App.getACache().put(Constant.KEY_GESTURE_PASSWORD_VERIFY,"");
        super.onDestroy();
    }


    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (index != 0) {
                mBtnHome.performClick();
            }
            else if (mDrawerLayout.isDrawerOpen(mLlLeft)) {
                mDrawerLayout.closeDrawer(mLlLeft);
            }
            else {
                //moveTaskToBack(false);
                if ((System.currentTimeMillis() - DOUBLE_CLICK_TIME) > 2000) {
                    ToastUtils.showLongToast(
                            getString(R.string.double_click_exit));
                    //ToastUtils.showLongToast(
                    //        getString(R.string.double_click_exit));
                    DOUBLE_CLICK_TIME = System.currentTimeMillis();
                }
                else {
                    ((App) getApplication()).exitApp();
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override protected void onResume() {
        super.onResume();
    }

    //private void checkForUpdates() {
    //    DataManager.checkForUpdates(AppContext.APP_VERSION_CODE).
    //            subscribe(getNotProSubscribe(v -> {
    //                if (v.updateType.equals("2")) {
    //                    AlertDialog.Builder alert1 = new AlertDialog.Builder(
    //                            mContext).setTitle("检测到新版本,是否更新")
    //                                     .setMessage(v.updateInfo)
    //                                     .setPositiveButton("更新",
    //                                             (dialog, which) -> {
    //                                                 mProgressDialogUtils.showProgress(
    //                                                         "下载更新中");
    //                                                 UpdaterConfig config
    //                                                         = new UpdaterConfig.Builder(
    //                                                         this).setTitle(
    //                                                         getResources().getString(
    //                                                                 R.string.app_name))
    //                                                              .setDescription(
    //                                                                      getString(
    //                                                                              R.string.system_download_description))
    //                                                              .setFileUrl(
    //                                                                      v.downloadUrl)
    //                                                              .setCanMediaScanner(
    //                                                                      true)
    //                                                              .build();
    //                                                 Updater.get()
    //                                                        .showLog(
    //                                                                Constant.DEBUG)
    //                                                        .download(config);
    //                                             });
    //                    alert1.setCancelable(false);
    //                    alert1.create();
    //                    alert1.show();
    //                }
    //                else if (v.updateType.equals("1")) {
    //                    AlertDialog.Builder alert = new AlertDialog.Builder(
    //                            mContext).setTitle("检测到新版本,是否更新")
    //                                     .setMessage(v.updateInfo)
    //                                     .setNegativeButton(
    //                                             android.R.string.cancel,
    //                                             (dialogInterface, i) -> {
    //                                                 dialogInterface.dismiss();
    //                                             })
    //                                     .setPositiveButton("更新",
    //                                             (dialog, which) -> {
    //                                                 mProgressDialogUtils.showProgress(
    //                                                         "下载更新中");
    //                                                 UpdaterConfig config
    //                                                         = new UpdaterConfig.Builder(
    //                                                         this).setTitle(
    //                                                         getResources().getString(
    //                                                                 R.string.app_name))
    //                                                              .setDescription(
    //                                                                      getString(
    //                                                                              R.string.system_download_description))
    //                                                              .setFileUrl(
    //                                                                      v.downloadUrl)
    //                                                              .setCanMediaScanner(
    //                                                                      true)
    //                                                              .build();
    //                                                 Updater.get()
    //                                                        .showLog(
    //                                                                Constant.DEBUG)
    //                                                        .download(config);
    //                                             });
    //                    alert.setCancelable(false);
    //                    alert.create();
    //                    alert.show();
    //                }
    //            }));
    //}
    //
    //
    //@Subscribe(threadMode = ThreadMode.MAIN)
    //public void installApkEvent(InstallApkEvent installApkEvent) {
    //    mProgressDialogUtils.hideProgress();
    //    AlertDialog.Builder alert = new AlertDialog.Builder(mContext).setTitle(
    //            "温馨提示")
    //                                                                 .setMessage(
    //                                                                         "下载已完成,立即安装")
    //                                                                 .setNegativeButton(
    //                                                                         android.R.string.cancel,
    //                                                                         (dialogInterface, i) -> {
    //                                                                             dialogInterface
    //                                                                                     .dismiss();
    //                                                                         })
    //                                                                 .setPositiveButton(
    //                                                                         android.R.string.ok,
    //                                                                         (dialog, which) -> {
    //                                                                             dialog.dismiss();
    //                                                                             ApkInstallReceiver
    //                                                                                     .installApk(
    //                                                                                             mContext,
    //                                                                                             installApkEvent.apkPackageID);
    //                                                                         });
    //    alert.setCancelable(false);
    //    alert.create();
    //    alert.show();
    //}


    @Override protected void restartApp() {
        if (Constant.DEBUG) {
            return;
        }
        ToastUtils.showLongToast("应用被回收重启");
        // startActivity(new Intent(this, StartPageActivity.class));
        finish();
    }


    @Override protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int action = intent.getIntExtra(AppStatusConstant.KEY_HOME_ACTION,
                AppStatusConstant.ACTION_BACK_TO_HOME);
        switch (action) {
            case AppStatusConstant.ACTION_RESTART_APP:
                restartApp();
                break;
        }
    }


    @OnClick({ R.id.ivUserHead, R.id.tvSetting, R.id.tvCustomerService,
                     R.id.tvAbout, R.id.tvFeedback })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivUserHead:
                ActivityUtils.launchActivity(mContext, LoginActivity.class);
                //  ChangeAvatar();
                SelectPicWayDialogFragment selectPicWayDialogFragment
                        = SelectPicWayDialogFragment.newInstance("", "");
                //设置目标Fragment
                selectPicWayDialogFragment.show(getSupportFragmentManager(),
                        "selectPicWayDialogFragment");
                break;
            case R.id.tvSetting:
                ActivityUtils.launchActivity(mContext, SettingActivity.class);
                break;
            case R.id.tvCustomerService:
                ActivityUtils.launchActivity(mContext, WebActivity.class,
                        WebActivity.buildBundle(Constant.CUSTOMER_SERVICE_URL));
                break;
            case R.id.tvAbout:
                ActivityUtils.launchActivity(mContext, AboutActivity.class);
                break;
            case R.id.tvFeedback:
                ActivityUtils.launchActivity(mContext, FeedbackActivity.class);
                break;
        }
    }


    private void ChangeAvatar() {
        RxGalleryFinalApi.getInstance(this)
                         .onCrop(true)//是否裁剪
                         .openGalleryRadioImgDefault(
                                 new RxBusResultDisposable() {
                                     @Override protected void onEvent(Object o)
                                             throws Exception {
                                         //Logger.i("只要选择图片就会触发");
                                     }
                                 })
                         .onCropImageResult(new IRadioImageCheckedListener() {
                             @Override public void cropAfter(Object t) {
                                 //mAvatarPath = t.toString();
                                 //GlideUtils.displayCircleHeader(mIvUserHead,
                                 //        mAvatarPath);
                                 ////Log.d("result", "裁剪路径1" + mAvatarPath);
                                 //ImageCompressUtils.from(mContext)
                                 //                  .load(mAvatarPath)
                                 //                  .execute(
                                 //                          new ImageCompressUtils.OnCompressListener() {
                                 //                              @Override
                                 //                              public void onSuccess(File file) {
                                 //                                  mAvatarPath
                                 //                                          = file
                                 //                                          .getAbsolutePath();
                                 //                                  //Log.d("result",
                                 //                                  //        "压缩路径" +
                                 //                                  //                mAvatarPath);
                                 //                              }
                                 //
                                 //
                                 //                              @Override
                                 //                              public void onError(Throwable e) {
                                 //                                  Log.d("result",
                                 //                                          "图片选择失败");
                                 //                              }
                                 //                          });
                                 //DataManager.editAvatar(mAvatarPath)
                                 //           .compose(bindToLifecycle())
                                 //           .subscribe(getSubscribe(
                                 //                   R.string.editIng,
                                 //                   s -> editSuccess(s)));
                             }


                             @Override public boolean isActivityFinish() {
                                 Logger.i("返回false不关闭，返回true则为关闭");
                                 return true;
                             }
                         });
    }
}
