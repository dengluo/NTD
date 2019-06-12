package com.qiangu.ntd.app;

import androidx.multidex.MultiDexApplication;
import com.qiangu.ntd.base.BaseAppManager;
import com.qiangu.ntd.base.utils.ACache;
import com.qiangu.ntd.base.utils.NetworkConfig;
import com.qiangu.ntd.dao.ReturnCode;
import com.qiangu.ntd.dao.ReturnCodeConfig;
import com.qiangu.ntd.dao.cache.DataProvider;

/**
 * 应用
 *
 * @author jianhao025@gmail.com
 * @data: 2016/07/27 08:59
 * @version: V1.0
 */
public class App extends MultiDexApplication {
    private static App mInstance;


    public static synchronized App getInstance() {
        return mInstance;
    }


    private ACache mACache;


    public void onCreate() {
        super.onCreate();
        App.mInstance = this;
        DataProvider.init(this);
        mACache = ACache.get(this);
        //AppContext  init一定要放  ACache.get(this)后面
        AppContext.getInstance().init(this);
        NetworkConfig.setBaseUrl(Constant.HOST_URL);
        NetworkConfig.setCacheFile(
                DirContext.getInstance().getDir(DirContext.DirEnum.CACHE),
                Constant.NETWORK_URL_CACHE_SIZE);
        ReturnCodeConfig.getInstance()
                        .initReturnCode(ReturnCode.CODE_SUCCESS,
                                ReturnCode.CODE_EMPTY);
    }


    @Override public void onLowMemory() {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onLowMemory();
    }


    public void exitApp() {
        //App.getACache().put(Constant.KEY_GESTURE_PASSWORD_VERIFY,"");
        BaseAppManager.getInstance().clear();
        System.gc();
        android.os.Process.killProcess(android.os.Process.myPid());
    }


    public static ACache getACache() {
        return getInstance().mACache;
    }
}
