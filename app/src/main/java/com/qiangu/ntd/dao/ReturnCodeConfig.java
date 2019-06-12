package com.qiangu.ntd.dao;

/**
 * 返回码配置
 * @author jianhao025@gmail.com
 * @data: 2016/07/27 08:59
 * @version: V1.0
 */
public class ReturnCodeConfig {

	/** 空数据返回码 */
	public static String CODE_EMPTY = "-0x1000";
	public static String CODE_SUCCESS = "-0x1200";
	public static String CODE_LOCAL_EMPTY = "-0x1000";
	/** 无网络返回码 */
	public static final String CODE_NO_NETWORK = "-0x1001";
	public static final String GESTURE_CANCEL = "0x0001";
	
	private static ReturnCodeConfig instance;
	
	public String successCode;
	
	private ReturnCodeConfig() {
		successCode ="0";
	}
	
	public static ReturnCodeConfig getInstance() {
		if(instance == null) {
			instance = new ReturnCodeConfig();
		}
		return instance;
	}
	
	/**
	 * 设置成功的返回码, 用来判断
	 * @param successCode
	 */
	public void initReturnCode(String successCode, String emptyCode) {
		this.successCode = successCode;
		ReturnCodeConfig.CODE_SUCCESS = successCode;
		ReturnCodeConfig.CODE_EMPTY = emptyCode;
	}
	
	public String getEmptyCode() {
		return CODE_LOCAL_EMPTY;
	}
	
	/**
	 * 是否数据位空
	 * @param code
	 * @return
	 */
	public boolean isEmptyCode(String code) {
		return code == CODE_EMPTY || code == CODE_LOCAL_EMPTY;
	}
	
	/**
	 * 判断网络是否成功
	 * @param code
	 * @return
	 */
	public boolean isSuccess(String code) {
		return code == successCode;
	}
	
	/**
	 * 判断是否无网络
	 * @param code
	 * @return
	 */
	public boolean isNoNetwork(String code) {
		return code == CODE_NO_NETWORK;
	}
	
}
