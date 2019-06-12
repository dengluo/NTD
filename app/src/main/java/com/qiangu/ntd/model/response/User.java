package com.qiangu.ntd.model.response;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * <用户实体类>
 *
 * @author fuxj
 * @data: 15/11/10 下午7:38
 * @version: V1.0
 */
public class User extends BaseResponse implements Serializable {
    @SerializedName("authenTicket") public String authenTicket;
    @SerializedName("authenUserId") public String authenUserId;
    @SerializedName("gestureFlag") public String gestureFlag;
    @SerializedName("headUrl") public String headUrl;
    @SerializedName("loginTime") public String loginTime;
    @SerializedName("mobile") public String mobile;
    @SerializedName("fullMobile") public String fullMobile;
    @SerializedName("nickname") public String nickname;
    @SerializedName("passwordErrorCnt") public int passwordErrorCnt;
    @SerializedName("payPasswordFlag") public String payPasswordFlag;
    @SerializedName("userId") public String userId;
    @SerializedName("realFlag") public String realFlag;
    @SerializedName("easemobId") public String easemobId;
    @SerializedName("bankMobile") public String bankMobile;
    public boolean isWxLogin;

    @SerializedName("fundBal") public double fundBal;
    @SerializedName("newUserFlag") public String newUserFlag;
    @SerializedName("receiveExpMoneyFlag") public String receiveExpMoneyFlag;
    @SerializedName("expMoneyIncomeFlag") public String expMoneyIncomeFlag;
    @SerializedName("loanStatus") public String loanStatus;
    @SerializedName("loanDueRepayAmt") public double loanDueRepayAmt;
    @SerializedName("loanRepayDate") public String loanRepayDate;
    @SerializedName("loanCnt") public double loanCnt;
    @SerializedName("loanUrl") public String loanUrl;
}
