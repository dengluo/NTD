package com.qiangu.ntd.dao.retrofit;

/**
 * @author jianhao025@gmail.com
 * @data: 2016/07/27 08:59
 * @version: V1.0
 */
public class ErrorThrowable extends Throwable {

    public String retStatus;

    public String errorMsg;
    public String errorCode;


    public ErrorThrowable(String retStatus, String errorCode, String errorMsg) {
        this.retStatus = retStatus;
        this.errorCode=errorCode;
        this.errorMsg = errorMsg;
    }

    public ErrorThrowable(String retStatus, String errorMsg) {
        this.retStatus = retStatus;
        this.errorMsg= errorMsg;
    }
    @Override public String toString() {
        return "retStatus:" + retStatus + ", errorMsg:" +errorMsg;
    }
}
