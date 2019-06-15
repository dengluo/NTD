package com.qiangu.ntd.model.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/12.
 */
public class Base extends BaseResponse implements Serializable{
    public Base(String msg, int code) {
        this.code =code;
        this.msg = msg;
    }
}
