package com.qiangu.ntd.model.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/12.
 */
public class Base extends BaseResponse implements Serializable{
    public Base(String retStatus, String errorMsg, String errorCode) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.retStatus = retStatus;
    }
}
