package com.qiangu.ntd.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/8/10.
 */
public class AppIdApplyRequest extends BaseRequest {
    @SerializedName("deviceId") public String deviceId;
    @SerializedName("payPassword") public String payPassword;


    public AppIdApplyRequest(String deviceId) {
        this.deviceId = deviceId;
    }


    public AppIdApplyRequest(String deviceId, String payPassword) {
        this.deviceId = deviceId;
        this.payPassword = payPassword;
    }
}
