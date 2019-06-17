package com.qiangu.ntd.app;

/**
 * 常量类
 *
 * @author jianhao025@gmail.com
 * @data: 2016/07/27 08:59
 * @version: V1.0
 */
public class Constant {

    public static final boolean DEBUG = true;

    private static final boolean USE_REAL_SERVER = false;

    private static final String SERVER_REAL
            = "https://tpurse.tempus.cn/tpurse/";//生产地址
    //http://172.18.126.209:8081/tpurse 预生产地址
    //http://172.18.126.209:8081
    //http://172.18.126.54:7088
    public static final String SERVER_TEST
            = "http://192.168.1.54:8088/";//测试地址
    //public static final String SERVER_TEST

    //        = "http://172.18.126.209:8081/tpurse/";// 预生产地址
    public static final String HOST_URL = USE_REAL_SERVER
                                          ? SERVER_REAL
                                          : SERVER_TEST;
    /**
     * 接口版本
     */
    public static final String KEY_DES = "wdvbjil/";
    public static final String LOCAL_KEY_DES = ")(*&^%$&";
    //请求网络的缓存文件大小
    public static final int NETWORK_URL_CACHE_SIZE = 1024 * 1024 * 100; //100M
    //图片缓存大小
    public static final int IMAGE_CACHE_SIZE = 1024 * 1024 * 100; //100M
    // 获取微信用户信息
    // RxBusEventName
    public static final String T_APP_PACKAGE_NAME = "com.tempus.wallet";//T钱包包名
    public static final int REQUEST_CODE_T_PAY = 0x210;//T钱包支付
    /**
     * 检查更新
     */
    /**
     * 下载apkId
     **/
    public static final String KEY_APK_PACKAGE_ID = "key_apk_package_id";

    //下载状态
    public static final String KEY_DOWNLOAD_STATE = "key_download_state";
    //SPConstant
    /**
     * APP的请求码
     */
    public static final String KEY_APP = "key_app";
    /**
     * APP访问令牌
     */
    public static final String KEY_ACCESS_TOKEN = "key_access_token";

    /**
     * uuid
     */
    public static final String KEY_UUID = "key_uuid";

    //用户信息
    public static final String KEY_USER = "user";
    public static final String KEY_USER_INFO = "user_info";
    public static final String KEY_GESTURE_PASSWORD = "gesture_password";
    public static final String KEY_VERSION_CODE = "version_code";
    public static final String KEY_PAY_TYPE = "pay_type";
    public static final String KEY_OPEN_PAYMENT = "open_payment";
    public static final String KEY_OPEN_SWEEP_CODE = "open_sweep_code";
    public static final String KEY_IS_DISPLAY = "is_display";
    //全局参数
    public static final String KEY_GLOBALPARAMS = "globalParams";
    public static final String KEY_HOME = "home";
    public static final String KEY_USERINFO = "userInfo";
    public static final String KEY_USERINFO_HEADURL = "userInfo_headUrl";
    public static final String KEY_HOME_TOPPICTURE = "home_top_picture";
    public static final String KEY_RECHARGERESPONSE = "rechargeResponse";

    public static final String KEY_IS_CHATACTIVITY = "is_chatActivity";
    public static final String KEY_GESTURE_PASSWORD_VERIFY
            = "gesture_password_verify";
    public static final String KEY_WIFI_UPDATE = "key_wifi_update";
    public static final String KEY_MESSAGE = "key_message";

    public static final int REQUEST_SMS_VERIFICATION_LOGIN = 0x14;
    public static final int REQUEST_CODE_REAL_NAME = 0x15;
    public static final int REUEST_CODDE = 0x10;
    public static final int REQUEST_CODE_PAY = 0x10;
    public static final int REQUEST_CODE_LOGIN = 0x11;
    public static final int REQUEST_CODE_IMAGE = 0x10;
    public static final int REQUEST_PAY_WORD_CODE = 1111;
    //43200*60
    /**
     * 选择系统图片Request Code
     */
    public static final int REQUEST_IMAGE = 112;
    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;
    public static final String CUSTOMER_SERVICE_URL = "https://ssl.pop800" +
            ".com/chat/510139";

    //http://api.pop800.com/chat/512350
}
