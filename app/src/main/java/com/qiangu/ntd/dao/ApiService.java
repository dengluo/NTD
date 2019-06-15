package com.qiangu.ntd.dao;

import com.qiangu.ntd.model.request.AppIdApplyRequest;
import com.qiangu.ntd.model.request.RefreshLoginRequest;
import com.qiangu.ntd.model.request.TokenApplyRequest;
import com.qiangu.ntd.model.response.AccessTokenInfo;
import com.qiangu.ntd.model.response.AppInfo;
import com.qiangu.ntd.model.response.Base;
import com.qiangu.ntd.model.response.User;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    //获取验证码
    @GET("userInfo/getVerifyCode") Observable<Base> getVerifyCode(
            @Query("tel") String tel);
    //验证验证码
    @FormUrlEncoded
    @POST("userInfo/verifyCode") Observable<Base> verifyCode(
            @Field("tel") String tel, @Field("code") String code);

    //用户注册
    @FormUrlEncoded
    @POST("userInfo/registUser") Observable<Base> registUser(
            @Field("tel") String tel, @Field("ntdNo") String ntdNo,
            @Field("password") String password, @Field("repPassword") String repPassword);
    //用户登录
    @FormUrlEncoded
    @POST("userInfo/login") Observable<User> login(
            @Field("ntdNo") String ntdNo,  @Field("password") String password);
    //用户退出登录
    @GET("userInfo/logout") Observable<Base> loginOut(
            @Query("token") String token);
}
