package com.qiangu.ntd.model.event;

import com.qiangu.ntd.model.BaseInfo;

/**
 * <>
 *
 * @author chenml@cncn.com
 * @data: 2016/4/22 22:17
 * @version: V1.0
 */
public class InstallApkEvent extends BaseInfo {

    public long apkPackageID;

    public InstallApkEvent(long apkPackageID) {
        this.apkPackageID = apkPackageID;
    }

}
