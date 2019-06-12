package com.qiangu.ntd.manager;

import com.qiangu.ntd.app.AppContext;
import com.qiangu.ntd.dao.ResponseHandle;
import com.qiangu.ntd.dao.retrofit.RetrofitDao;
import com.qiangu.ntd.model.request.AppIdApplyRequest;
import com.qiangu.ntd.model.request.RefreshLoginRequest;
import com.qiangu.ntd.model.request.TokenApplyRequest;
import com.qiangu.ntd.model.response.AccessTokenInfo;
import com.qiangu.ntd.model.response.AppInfo;
import com.qiangu.ntd.model.response.User;
import io.reactivex.Observable;
import java.util.List;

/**
 * @author jianhao025@gmail.com
 * @data: 2016/07/27 08:59
 * @version: V1.0
 */
public class DataManager {

    private static DataManager ourInstance;


    // 单例写法---双重检查锁模式
    public static DataManager getInstance() {
        if (ourInstance == null) {
            synchronized (DataManager.class) {
                if (ourInstance == null) ourInstance = new DataManager();
            }
        }
        return ourInstance;
    }


    //2.1 申请appId和appSerect
    public static Observable<AppInfo> getAppInfo() {
        return RetrofitDao.getInstance()
                          .getApiService()
                          .getAppInfo(
                                  new AppIdApplyRequest(AppContext.DEVICE_ID));
    }


    //2.2 获取访问令牌
    public static Observable<AccessTokenInfo> getTokenInfo(TokenApplyRequest tokenApplyRequest) {
        return RetrofitDao.getInstance()
                          .getApiService()
                          .getTokenInfo(tokenApplyRequest);
    }


    //2.2 获取访问令牌
    public static Observable<User> refreshLogin(List<String> params) {
        return RetrofitDao.getInstance()
                          .getApiService()
                          .refreshLogin(new RefreshLoginRequest(params.get(0),
                                  params.get(1)))
                          .flatMap(ResponseHandle.newEntityData());
    }

}
