package com.qiangu.ntd.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/9/4.
 */

public class LoginRequest extends BaseRequest{
    @SerializedName("ntdNo") public String ntdNo;
    @SerializedName("password") public String password;
    public LoginRequest(String ntdNo, String password) {
        this.ntdNo = ntdNo;
        this.password = password;
    }
}
