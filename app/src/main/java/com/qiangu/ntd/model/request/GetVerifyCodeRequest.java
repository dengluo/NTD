package com.qiangu.ntd.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/9/4.
 */

public class GetVerifyCodeRequest extends BaseRequest{
    @SerializedName("tel") public String tel;


    public GetVerifyCodeRequest(String tel) {
        this.tel = tel;
    }
}
