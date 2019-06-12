package com.qiangu.ntd.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/9/4.
 */

public class LoginRequest extends BaseRequest{
    @SerializedName("mobile") public String mobile;
    @SerializedName("verifyCode") public String verifyCode;
    @SerializedName("graphicVerifyCode") public String graphicVerifyCode;


    public LoginRequest(String mobile, String verifyCode) {
        this.mobile = mobile;
        this.verifyCode = verifyCode;
    }

    public LoginRequest(String mobile, String verifyCode, String graphicVerifyCode) {
        this.mobile = mobile;
        this.verifyCode = verifyCode;
        this.graphicVerifyCode = graphicVerifyCode;
    }
}
