package com.qiangu.ntd.model;

import com.google.gson.annotations.SerializedName;
import com.qiangu.ntd.dao.ReturnCodeConfig;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/10.
 */
public class ResponseInfo implements Serializable{
    @SerializedName("code") public String code;
    @SerializedName("msg") public String msg;


    public boolean isSuccess() {
        return code.equals(ReturnCodeConfig.getInstance().successCode);
    }


    public boolean isEmptyCode() {
        return ReturnCodeConfig.getInstance().isEmptyCode(code);
    }
}
