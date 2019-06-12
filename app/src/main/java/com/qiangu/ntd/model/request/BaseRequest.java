package com.qiangu.ntd.model.request;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.qiangu.ntd.manager.TokenManager;
import com.qiangu.ntd.manager.UserManager;

/**
 * Created by Administrator on 2016/8/11.
 */
public abstract class BaseRequest {

    //文档必须的
    @SerializedName("channel") public String channel = "T";
    @SerializedName("accessSource") public String accessSource = "3";
    //Build.VERSION.SDK; // 设备SDK版本（Android版本号）
    // Build.VERSION.RELEASE;// 设备的系统版本
    //android.os.Build.MODEL ：获取手机的型号 设备名称。
    //android.os.Build.MANUFACTURER:获取设备制造商

    @SerializedName("accessSourceType") public String accessSourceType =
            android.os.Build.MANUFACTURER + " " + android.os.Build.MODEL + "," +
                    "Android " + android.os.Build.VERSION.RELEASE + "," +
                    "API " +
                    android.os.Build.VERSION.SDK;
    @SerializedName("version") public String version = "1.0";
    @SerializedName("accessToken") public String accessToken
            = (TokenManager.getInstance().getSimpleTokenInfo() != null &&
            !TextUtils.isEmpty(TokenManager.getInstance()
                                           .getSimpleTokenInfo().accessToken))
              ? TokenManager.getInstance().getSimpleTokenInfo().accessToken
              : "";
    @SerializedName("userId") public String userId = UserManager.getInstance()
                                                                .isLogin()
                                                     ? UserManager.getInstance()
                                                                  .getUser().userId
                                                     : "";
    @SerializedName("serviceCode") public String serviceCode;
    @SerializedName("localDateTime") public String localDateTime;
    @SerializedName("curUserId") public String curUserId;
    @SerializedName("externalReferenceNo") public String externalReferenceNo;
    @SerializedName("userReferenceNo") public String userReferenceNo;
    @SerializedName("stepCode") public String stepCode = "1";

    @SerializedName("packNo") public String packNo;
    @SerializedName("seqNo") public String seqNo;
    @SerializedName("templateNo") public String templateNo;
}


