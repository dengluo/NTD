package com.qiangu.ntd.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/11/9.
 */

public class RefreshLoginRequest extends BaseRequest {
    @SerializedName("ticket") public String ticket;
    @SerializedName("timestamp") public String timestamp;


    public  RefreshLoginRequest(String  ticket, String timestamp) {
        this.ticket = ticket;
        this.timestamp=timestamp;
    }
}

