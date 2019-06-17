package com.qiangu.ntd.model.response;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author Danny
 * @Date 2019/6/17 10:55
 * @Des Recharge充值实体类
 */
public class Recharge extends BaseResponse implements Serializable {
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

    public Recharge(String bbAddress) {
        this.bbAddress = bbAddress;
    }

}