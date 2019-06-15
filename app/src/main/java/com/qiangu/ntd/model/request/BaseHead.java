package com.qiangu.ntd.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/8/12.
 */
public class BaseHead extends BaseRequest {
    @SerializedName("filterFlag") public String filterFlag;


    public BaseHead() {
    }

    //public BaseHead(String version, String filterFlag) {
    //    this.filterFlag = filterFlag;
    //}


}
