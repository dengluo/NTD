package com.qiangu.ntd.manager;

import com.qiangu.ntd.app.AppContext;
import com.qiangu.ntd.base.RxSchedulers;
import com.qiangu.ntd.dao.ResponseHandle;
import com.qiangu.ntd.dao.retrofit.RetrofitDao;
import com.qiangu.ntd.model.request.AppIdApplyRequest;
import com.qiangu.ntd.model.request.LoginRequest;
import com.qiangu.ntd.model.request.RefreshLoginRequest;
import com.qiangu.ntd.model.request.RegistRequest;
import com.qiangu.ntd.model.request.TokenApplyRequest;
import com.qiangu.ntd.model.request.VerifyCodeRequest;
import com.qiangu.ntd.model.response.AccessTokenInfo;
import com.qiangu.ntd.model.response.AppInfo;
import com.qiangu.ntd.model.response.Base;
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
    //使用手机短信登录
    //public static Observable<User> login(String mobile, String verifyCode, String graphicVerifyCode) {
    //    return RetrofitDao.getInstance()
    //                      .getApiService()
    //                      .login(new LoginRequest(
    //                              mobile,
    //                              verifyCode,
    //                              graphicVerifyCode))
    //                       .flatMap(ResponseHandle.newEntityData())
    //                       .retry(ResponseHandle.canRetry())
    //                       .compose(RxSchedulers.io_main());
    //}


    public static Observable<Base> getVerifyCode(String tel) {
        return RetrofitDao.getInstance()
                          .getApiService()
                          .getVerifyCode(tel)
                          .flatMap(ResponseHandle.newEntityData())
                          //.retry(ResponseHandle.canRetry())
                          .compose(RxSchedulers.io_main());
    }


    public static Observable<Base> verifyCode(String tel, String code) {
        return RetrofitDao.getInstance()
                          .getApiService()
                          .verifyCode(new VerifyCodeRequest(tel, code))
                          .flatMap(ResponseHandle.newEntityData())
                          //.retry(ResponseHandle.canRetry())
                          .compose(RxSchedulers.io_main());
    }


    public static Observable<Base> registUser(String tel, String ntdNo, String password, String repPassword) {
        return RetrofitDao.getInstance()
                          .getApiService()
                          .registUser(new RegistRequest(tel, ntdNo, password,
                                  repPassword))
                          .flatMap(ResponseHandle.newEntityData())
                          //.retry(ResponseHandle.canRetry())
                          .compose(RxSchedulers.io_main());
    }


    public static Observable<User> login(String ntdNo, String password) {
        return RetrofitDao.getInstance()
                          .getApiService()
                          .login(new LoginRequest(ntdNo, password))
                          .flatMap(ResponseHandle.newEntityData())
                          //.retry(ResponseHandle.canRetry())
                          .compose(RxSchedulers.io_main());
    }
}
