package com.qiangu.ntd.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/20.
 */

public class TransferInfo implements Serializable {
    @SerializedName("money") public String money;
    @SerializedName("status") public int status;
    @SerializedName("codeId") public String codeId;
    @SerializedName("nickname") public String nickname;
    @SerializedName("headUrl") public String headUrl;

    @SerializedName("type") public String type;
    //@SerializedName("content") public String content;
    @SerializedName("content") public ContentBean content;

    public static class ContentBean implements Serializable {
        @SerializedName("resultStatus") public String resultStatus;
        @SerializedName("message") public String message;
        @SerializedName("iconUrl") public String iconUrl;
        @SerializedName("tranName") public String tranName;
        @SerializedName("bankIconUrl") public String bankIconUrl;
        @SerializedName("bankName") public String bankName;
        @SerializedName("bankCardNo") public String bankCardNo;
        @SerializedName("tranAmt") public Double tranAmt;
    }

}
