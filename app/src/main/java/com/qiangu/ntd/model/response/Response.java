package com.qiangu.ntd.model.response;

import com.google.gson.annotations.SerializedName;
import com.qiangu.ntd.model.ResponseInfo;

/**
 * <网络请求返回实体类>
 *
 * @author chenml@cncn.com
 * @data: 2015/11/16 20:31
 * @version: V1.0
 */
public class Response<T> {

    @SerializedName("head") public ResponseInfo
            mResponseInfo;

    @SerializedName("data") public T data;
}
