package com.qiangu.ntd.model.request;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

/**
 * @Author Danny
 * @Date 2019/6/15 12:00
 * @Des 充值接口
 */

public class RechargeRequest extends BaseRequest {
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


    //获取币币钱包地址
    public RechargeRequest(String msg, int code, String bbAddress) {
        this.msg = msg;
        this.code = code;
        this.bbAddress = bbAddress;
    }

    //查询钱包地址是否存在
    public RechargeRequest(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    //NTD充值接口
    public RechargeRequest(String bbAddress, String account, BigDecimal ntdAmount, String thirdRechargeNo, String sign) {
        this.bbAddress = bbAddress;
        this.account = account;
        this.ntdAmount = ntdAmount;
        this.thirdRechargeNo = thirdRechargeNo;
        this.sign = sign;
    }

    //根据NTD充值流水号查询充值记录
    public RechargeRequest(String rechargeNo) {
        this.rechargeNo = rechargeNo;
    }
}

