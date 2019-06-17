package com.qiangu.ntd.model.response;

import com.google.gson.annotations.SerializedName;
import com.qiangu.ntd.dao.ReturnCodeConfig;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/8/11.
 */
public class RechargeResponse extends BaseResponse implements Serializable {
    //返回描述
    @SerializedName("msg")
    public String msg;
    //状态码
    @SerializedName("code")
    public int code;
    //币币钱包地址
    @SerializedName("bbAddress")
    public String bbAddress;
    //帕劳账户名
    @SerializedName("account")
    public String account;
    //本次充值NTD数量
    @SerializedName("ntdAmount")
    public BigDecimal ntdAmount;
    //第三方充值流水号
    @SerializedName("thirdRechargeNo")
    public String thirdRechargeNo;
    //参数签名
    @SerializedName("sign")
    public String sign;
    //ntd 充值流水号
    @SerializedName("rechargeNo")
    public String rechargeNo;
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

