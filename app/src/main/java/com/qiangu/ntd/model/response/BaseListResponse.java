package com.qiangu.ntd.model.response;

import com.google.gson.annotations.SerializedName;
import com.qiangu.ntd.dao.ReturnCodeConfig;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/11.
 */
public abstract class BaseListResponse implements Serializable {
    @SerializedName("errorCode") public String errorCode;
    @SerializedName("errorMsg") public String errorMsg;
    @SerializedName("retStatus") public String retStatus;
    public boolean isSuccess() {
        return retStatus.equals(ReturnCodeConfig.getInstance().successCode);
    }


    public boolean isEmptyCode() {
        return ReturnCodeConfig.getInstance().isEmptyCode(retStatus);
    }
}
