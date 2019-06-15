package com.qiangu.ntd.dao;

import android.util.Log;
import com.google.gson.JsonParseException;
import com.qiangu.ntd.R;
import com.qiangu.ntd.app.AppContext;
import com.qiangu.ntd.app.Constant;
import com.qiangu.ntd.dao.exception.EncryptException;
import com.qiangu.ntd.dao.exception.NoNetworkException;
import com.qiangu.ntd.dao.exception.RefreshLoginException;
import com.qiangu.ntd.dao.retrofit.ErrorThrowable;
import com.qiangu.ntd.model.response.BaseResponse;
import com.qiangu.ntd.model.response.ListData;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.Function;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLHandshakeException;

/**
 * @author jianhao025@gmail.com
 * @data: 2016/07/27 08:59
 * @version: V1.0
 */
public class ResponseHandle {

    private static final String TAG = "ResponseHandle";

    //请求token失败重连的次数
    private static final int RETRY_TIME = 4;


    private static String getString(int resId) {
        return AppContext.getString(resId);
    }


    public static <T> Function<Throwable, ? extends Observable<? extends T>> errorResumeFunc() {
        return throwable -> {
            if (throwable instanceof JsonParseException) {
                return Observable.error(
                        new ErrorThrowable(ReturnCode.LOCAL_GSON_ERROR,
                                getString(Constant.DEBUG
                                          ? R.string.error_gson_parse_error
                                          : R.string.error_system_error)));
            }
            else if (throwable instanceof UnknownHostException ||
                    throwable instanceof ConnectException ||
                    throwable instanceof SocketTimeoutException) {
                //                return Observable.error(new ErrorThrowable(ReturnCode.LOCAL_UNKNOWN_HOST_ERROR, AppContext.getString(R.string.error_unknown_host)));
                return Observable.error(
                        new ErrorThrowable(ReturnCode.LOCAL_UNKNOWN_HOST_ERROR,
                                Constant.DEBUG
                                ? throwable.toString()
                                : AppContext.getString(
                                        R.string.error_unknown)));
            }
            else if (throwable instanceof NoNetworkException) {
                return Observable.error(
                        new ErrorThrowable(ReturnCode.LOCAL_NO_NETWORK,
                                AppContext.getString(R.string.no_network)));
            }
            else if (throwable instanceof EncryptException) {
                return Observable.error(
                        new ErrorThrowable(ReturnCode.LOCAL_ENCRYPT_ERROR,
                                AppContext.getString(R.string.error_encrypt)));
            }
            else if (throwable instanceof RefreshLoginException) {
                return Observable.error(
                        new ErrorThrowable(ReturnCode.LOCAL_REFRESH_LOGIN_ERROR,
                                AppContext.getString(
                                        R.string.error_login_timeout)));
            }
            else if (throwable instanceof SSLHandshakeException) {
                return Observable.error(
                        new ErrorThrowable(ReturnCode.LOCAL_NO_NETWORK,
                                AppContext.getString(R.string.no_network)));
            }
            else if (throwable instanceof ErrorThrowable) {
                return Observable.error(throwable);
            }
            else {
                //                return Observable.error(new ErrorThrowable(ReturnCode.LOCAL_UNKNOWN_ERROR, AppContext.getString(R.string.error_unknown)));
                return Observable.error(
                        new ErrorThrowable(ReturnCode.LOCAL_UNKNOWN_ERROR,
                                Constant.DEBUG
                                ? (throwable == null
                                   ? AppContext.getString(
                                        R.string.error_unknown)
                                   : throwable.toString())
                                : AppContext.getString(
                                        R.string.error_unknown)));
            }
        };
    }


    //是否重试
    public static BiPredicate<Integer, Throwable> canRetry() {
        return (retryTime, throwable) -> {
            boolean retry = false;
            if (retryTime > ResponseHandle.RETRY_TIME) {
                retry = false;
            }
            else {
                if (throwable instanceof ErrorThrowable) {
                    retry = true;
                    ErrorThrowable errorThrowable = (ErrorThrowable) throwable;
                    //当errorCode==""  时会产生异常 转换异常

                    //if (TextUtils.isEmpty(errorThrowable.code)) {
                    //    retry = false;
                    //}
                    //else {
                    //    switch (errorThrowable.code) {
                    //        case ReturnCode.CODE_INVALIDATED_TOKEN:  //token失效
                    //            Log.e(TAG, "token失效");
                    //            TokenManager.getInstance().resetToken();
                    //            break;
                    //        case ReturnCode.CODE_INVALIDATED_LOGIN:  //登录信息过期
                    //            Log.e(TAG, "登录信息失效");
                    //            TokenManager.getInstance().setRefreshLogin();
                    //            break;
                    //
                    //        case ReturnCode.CODE_USER_FREEZE:  //用户冻结
                    //            Log.e(TAG, "用户冻结");
                    //            retry = false;
                    //            break;
                    //
                    //        case ReturnCode.CODE_EMPTY:  //数据为空
                    //            Log.e(TAG, "数据为空-------------=-");
                    //            retry = false;
                    //            break;
                    //        default:
                    //            retry = false;
                    //            break;
                    //        //}
                    //    }
                    //}
                }
            }
            return retry;
        };
    }


    // 读取实体数据
    private static class ReadDataFunc<E> implements Function<E, Observable<E>> {
        @Override public Observable<E> apply(@NonNull E x) throws Exception {
            BaseResponse baseResponse = ((BaseResponse) x);
            if (baseResponse.code==ReturnCode.CODE_SUCCESS) {
                return Observable.just(x);
            }
            else {
                return Observable.error(
                        new ErrorThrowable(
                                baseResponse.code, baseResponse.msg));
            }
        }
    }



    // 读取列表数据
    public static class ReadListFunc<E>
            implements Function<ListData<E>, Observable<ListData<E>>> {
        @Override public Observable<ListData<E>> apply(@NonNull ListData<E> x)
                throws Exception {
            if (x.code==ReturnCode.CODE_SUCCESS) {
                if (x.list.size() != 0) {
                    return Observable.just(x);
                }
                else {
                    //Log.d("result", "数据为空----------");
                    //{"errorCode":"","errorMsg":"","list":[],"retStatus":"0"}
                    return Observable.error(new ErrorThrowable(x.code, "数据为空"));
                    //return Observable.error(
                    //        new ErrorThrowable(ReturnCode.CODE_EMPTY,
                    //                x.errorCode, "数据为空"));
                }
            }
            else {
                return Observable.error(
                        new ErrorThrowable(x.code,
                                x.msg));
            }
        }
    }


    //新建处理实体类型数据
    public static ReadDataFunc newEntityData() {
        return new ReadDataFunc();
    }


    //新建处理列表类型数据
    public static ReadListFunc newListData() { return new ReadListFunc(); }
}
