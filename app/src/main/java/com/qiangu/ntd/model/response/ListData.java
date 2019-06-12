package com.qiangu.ntd.model.response;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author ml_bright
 * @email 2504509903@qq.com
 * @date 2015-10-16 下午4:14:37 
 * @version V1.0
 */
public class ListData<T> extends BaseResponse implements Serializable{

	@SerializedName("list")
	public List<T> list;

	@SerializedName("hasMore")
	public String hasMore;
	public boolean isHasMore(){
		return hasMore.equals("1");
	}
}
