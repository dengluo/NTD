package com.qiangu.ntd.model;

import com.google.gson.annotations.SerializedName;
import com.qiangu.ntd.model.response.BaseResponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/26.
 */

public class Home extends BaseResponse implements Serializable {

    @SerializedName("fundUrl") public String fundUrl;
    @SerializedName("incomeRatio") public String incomeRatio;
    @SerializedName("loanUrl") public String loanUrl;
    @SerializedName("privateFundUrl") public String privateFundUrl;
    @SerializedName("bannerInfos") public List<BannerInfosBean> bannerInfos;
    @SerializedName("prgInfos") public List<PrgInfosBean> prgInfos;
    @SerializedName("balUrl") public String balUrl;
    @SerializedName("topPicture") public String topPicture;
    @SerializedName("topNeedLogin") public String topNeedLogin;
    @SerializedName("topOpenMode") public String topOpenMode;
    @SerializedName("topHref") public String topHref;

    //BannerInfosBean必须implements Serializable
    public static class BannerInfosBean implements Serializable {
        @SerializedName("href") public String href;
        @SerializedName("picture") public String picture;
        @SerializedName("needLogin") public String needLogin;
        @SerializedName("openMode") public String openMode;
    }

    public static class PrgInfosBean implements Serializable {
        @SerializedName("increaseRate") public String increaseRate;
        @SerializedName("period") public String period;
        @SerializedName("prgDetailHref") public String prgDetailHref;
        @SerializedName("prgRate") public String prgRate;
        @SerializedName("repayMod") public String repayMod;
    }

    //private List<BannerInfosBean> mBannerInfos=new ArrayList<>();
    //mBannerInfos


    public Home() {
        this.fundUrl = "";
        this.incomeRatio = "";
        this.loanUrl = "";
        this.privateFundUrl = "";
        this.bannerInfos = new ArrayList<>();
        this.prgInfos = new ArrayList<>();
        this.balUrl = "";
        this.topPicture = "";
        this.topNeedLogin = "";
        this.topOpenMode = "";
        this.topHref = "";
    }


    public Home(String fundUrl, String incomeRatio, String loanUrl, String privateFundUrl, List<BannerInfosBean> bannerInfos, List<PrgInfosBean> prgInfos, String balUrl, String topPicture, String topNeedLogin, String topOpenMode, String topHref) {
        this.fundUrl = fundUrl;
        this.incomeRatio = incomeRatio;
        this.loanUrl = loanUrl;
        this.privateFundUrl = privateFundUrl;
        this.bannerInfos = bannerInfos;
        this.prgInfos = prgInfos;
        this.balUrl = balUrl;
        this.topPicture = topPicture;
        this.topNeedLogin = topNeedLogin;
        this.topOpenMode = topOpenMode;
        this.topHref = topHref;
    }


    public String getFundUrl() {
        return fundUrl;
    }


    public void setFundUrl(String fundUrl) {
        this.fundUrl = fundUrl;
    }


    public String getIncomeRatio() {
        return incomeRatio;
    }


    public void setIncomeRatio(String incomeRatio) {
        this.incomeRatio = incomeRatio;
    }


    public String getLoanUrl() {
        return loanUrl;
    }


    public void setLoanUrl(String loanUrl) {
        this.loanUrl = loanUrl;
    }


    public String getPrivateFundUrl() {
        return privateFundUrl;
    }


    public void setPrivateFundUrl(String privateFundUrl) {
        this.privateFundUrl = privateFundUrl;
    }


    public List<BannerInfosBean> getBannerInfos() {
        return bannerInfos;
    }


    public void setBannerInfos(List<BannerInfosBean> bannerInfos) {
        this.bannerInfos = bannerInfos;
    }


    public List<PrgInfosBean> getPrgInfos() {
        return prgInfos;
    }


    public void setPrgInfos(List<PrgInfosBean> prgInfos) {
        this.prgInfos = prgInfos;
    }


    public String getBalUrl() {
        return balUrl;
    }


    public void setBalUrl(String balUrl) {
        this.balUrl = balUrl;
    }


    public String getTopPicture() {
        return topPicture;
    }


    public void setTopPicture(String topPicture) {
        this.topPicture = topPicture;
    }


    public String getTopNeedLogin() {
        return topNeedLogin;
    }


    public void setTopNeedLogin(String topNeedLogin) {
        this.topNeedLogin = topNeedLogin;
    }


    public String getTopOpenMode() {
        return topOpenMode;
    }


    public void setTopOpenMode(String topOpenMode) {
        this.topOpenMode = topOpenMode;
    }


    public String getTopHref() {
        return topHref;
    }


    public void setTopHref(String topHref) {
        this.topHref = topHref;
    }
}
