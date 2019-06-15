package com.qiangu.ntd.model.response;

import com.google.gson.annotations.SerializedName;
import com.qiangu.ntd.dao.ReturnCodeConfig;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/11.
 */
public abstract class BaseResponse implements Serializable {
    @SerializedName("code") public int code;
    @SerializedName("msg") public String msg;
    //@SerializedName("retStatus") public String retStatus;
    public boolean isSuccess() {
        return code==ReturnCodeConfig.getInstance().successCode;
        //注意上面不是赋值，是判断retStatus是否等于ReturnCodeConfig.getInstance()
        // .successCode，然后返回true或者false
    }


    //public boolean isEmptyCode() {
    //    return ReturnCodeConfig.getInstance().isEmptyCode(retStatus);
    //}
}

