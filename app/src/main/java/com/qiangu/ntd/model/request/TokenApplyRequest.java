package com.qiangu.ntd.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/8/11.
 */
public class TokenApplyRequest extends BaseRequest {
    @SerializedName("deviceId") public String deviceId;
    @SerializedName("appId") public String appId;
    @SerializedName("sign") public String sign;
    @SerializedName("timestamp") public String timestamp;

    public TokenApplyRequest(String deviceId, String appId, String sign,String timestamp) {
        this.deviceId = deviceId;
        this.appId = appId;
        this.sign = sign;
        this.timestamp=timestamp;
    }
}
