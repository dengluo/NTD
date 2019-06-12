package com.qiangu.ntd.dao;

import com.qiangu.ntd.model.request.AppIdApplyRequest;
import com.qiangu.ntd.model.request.RefreshLoginRequest;
import com.qiangu.ntd.model.request.TokenApplyRequest;
import com.qiangu.ntd.model.response.AccessTokenInfo;
import com.qiangu.ntd.model.response.AppInfo;
import com.qiangu.ntd.model.response.User;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author jianhao025@gmail.com
 * @data: 2016/07/27 08:59
 * @version: V1.0
 */
public interface ApiService {
    //申请appId和appSerect  /tpurse/app/appIdApply
    @POST("tpurse/app/appIdApply") Observable<AppInfo> getAppInfo(
            @Body AppIdApplyRequest appIdApplyRequest);

    //获取访问令牌
    @POST("tpurse/app/tokenApply") Observable<AccessTokenInfo> getTokenInfo(
            @Body TokenApplyRequest tokenApplyRequest);

    //刷新登录验证票
    @POST("tpurse/user/refreshLogin") Observable<User> refreshLogin(
            @Body RefreshLoginRequest refreshLoginRequest);
}
