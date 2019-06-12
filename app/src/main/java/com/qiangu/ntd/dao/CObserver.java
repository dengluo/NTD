package com.qiangu.ntd.dao;

import android.text.TextUtils;
import com.qiangu.ntd.app.Constant;
import com.qiangu.ntd.dao.retrofit.ErrorThrowable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author jianhao025@gmail.com
 * @data: 2016/07/27 08:59
 * @version: V1.0
 */
public abstract class CObserver<T> implements Observer<T> {

    @Override public void onComplete() {

    }


    @Override public void onSubscribe(Disposable d) {
        onPrepare();
    }


    @Override public void onError(Throwable e) {
        if (e == null) {
            //String retStatus, String errorMsg
            onError(new ErrorThrowable(ReturnCode.LOCAL_UNKNOWN_ERROR,
                    Constant.DEBUG ? "throwable is null" : ""));
        }
        else if (e instanceof ErrorThrowable) {
            onError(e);
            if (!TextUtils.isEmpty(((ErrorThrowable) e).errorCode) &&
                    ((ErrorThrowable) e).errorCode.equals(
                            ReturnCode.CODE_USER_FREEZE)) {
                //EventBus.getDefault().post(new LoginOutEvent());
            }
        }
        else {
            onError(new ErrorThrowable(ReturnCode.LOCAL_ERROR_TYPE_ERROR,
                    e.getMessage()));
        }
    }


    @Override public void onNext(T t) {
        onSuccess(t);
    }


    public abstract void onPrepare();

    public abstract void onError(ErrorThrowable throwable);

    public abstract void onSuccess(T t);
}
