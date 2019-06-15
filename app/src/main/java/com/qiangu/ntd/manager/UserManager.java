package com.qiangu.ntd.manager;

import android.content.Context;
import android.util.Log;
import com.qiangu.ntd.app.App;
import com.qiangu.ntd.app.Constant;
import com.qiangu.ntd.base.utils.ACache;
import com.qiangu.ntd.model.response.User;
import com.qiangu.ntd.model.response.UserInfo;
import io.reactivex.Observable;

/**
 * <用户管理类>
 *
 * @author chenml@cncn.com
 * @data: 2015/11/18 14:23
 * @version: V1.0
 */
public class UserManager {
    private static final String TAG = "UserManager";
    private static volatile UserManager mInstance;
    private User mUser;
    private UserInfo mUserInfo;
    private ACache mACache;


    public static UserManager getInstance() {
        if (mInstance == null) {
            synchronized (UserManager.class) {
                if (mInstance == null) {
                    mInstance = new UserManager();
                }
            }
        }
        return mInstance;
    }


    private UserManager() {
        mACache = App.getACache();
    }

    //public int getUserId() {
    //    if (mUser != null) {
    //        return mUser;
    //    }
    //    else {
    //        return 0;
    //    }
    //}

    //判断常登陆状态是否为微信登陆
    //public boolean isWxLogin() {
    //    return isLogin() && mUser.isWxLogin();
    //}


    public void fillUser() {
        // String userJson = SpUtil.getUserJson();
        User user = (User) mACache.getAsObject(Constant.KEY_USER);
        if (user != null) {
            this.mUser = user;
        }
        //User user = (User) mACache.getAsObject(Constant.KEY_USER_INFO);
        //if (user != null) {
        //    this.mUser = user;
        //}
    }


    public void setUser(User user) {
        try {
            //this.mMyInfo = null;
            this.mUser = user;
            if (user != null) {
                mACache.put(Constant.KEY_USER, user);
            }
            else {
                mACache.remove(Constant.KEY_USER);
            }
        } catch (Exception e) {
            Log.d(TAG, "setUserInfo=" + e);
        }
    }


    public void setUserInfo(UserInfo userInfo) {
        try {
            this.mUserInfo = userInfo;
            if (userInfo != null) {
                mACache.put(Constant.KEY_USER_INFO, userInfo);
            }
            else {
                mACache.remove(Constant.KEY_USER_INFO);
            }
        } catch (Exception e) {
            Log.d(TAG, "setUserInfo=" + e);
        }
    }


    public User getUser() {
        return mUser;
    }


    public UserInfo getUserInfo() {
        return mUserInfo;
    }


    public boolean isLogin() {
        return mUser != null;
    }


    public boolean isLogin(Context context) {
        if (mUser == null) {
            //ActivityUtils.launchActivity(context, LoginActivity.class);
            return false;
        }
        return true;
    }




    //登录
    public Observable<User> login(String mobile,String password) {
        return DataManager.login(mobile, password)
                          .flatMap(user -> {
                              setUser(user);
                              return Observable.just(user);
                          });
    }




    //第三方用户绑定手机号
    //public Observable<User> bindPhone(String unionId, String mobile, String smsCode) {
    //    return DataManager.bindPhone(unionId, mobile, smsCode).flatMap(user -> {
    //        setUserInfo(user);
    //        return Observable.just(user);
    //    });
    //}
    //
    //
    ////微信登录
    //public Observable<User> wxLogin(WxUserInfo wxUserInfo) {
    //    return DataManager.otherLogin(wxUserInfo).flatMap(userResponse -> {
    //        User user = userResponse;
    //        if (!TextUtils.isEmpty(userResponse.userId)) {
    //            user.isWxLogin = true;
    //            setUser(user);
    //        }
    //        return Observable.just(user);
    //    });
    //}
    ////qq登录
    //public Observable<User> qqLogin(QqUserInfo qqUserInfo) {
    //    return DataManager.otherLogin(qqUserInfo).flatMap(userResponse -> {
    //        User user = userResponse;
    //        user.isQq = true;
    //        setUserInfo(user);
    //        return Observable.just(user);
    //    });
    //}

    public static Observable<User> getRefreshLoginParams() {
        return Observable.create(subscriber -> {
            User user = UserManager.getInstance().getUser();
            String timestamp = String.valueOf(
                    System.currentTimeMillis() / 1000);
            subscriber.onNext(timestamp);
            subscriber.onComplete();
        })
                         .map(o -> (String) o)
                         .buffer(2)
                         .flatMap(strings -> DataManager.refreshLogin(strings))
                         .flatMap(user -> {
                             // String displayName = user.displayName;
                             UserManager.getInstance().setUser(user);
                             //  user.displayName = displayName;
                             return Observable.just(user);
                         });
    }
}