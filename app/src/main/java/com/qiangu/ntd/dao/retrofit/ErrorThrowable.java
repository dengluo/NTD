package com.qiangu.ntd.dao.retrofit;

/**
 * @author jianhao025@gmail.com
 * @data: 2016/07/27 08:59
 * @version: V1.0
 */
public class ErrorThrowable extends Throwable {
    public String msg;
    public int code;
    public ErrorThrowable(int code, String msg) {
        this.code=code;
        this.msg = msg;
    }

    public ErrorThrowable(String msg) {
        this.msg= msg;
    }

}
