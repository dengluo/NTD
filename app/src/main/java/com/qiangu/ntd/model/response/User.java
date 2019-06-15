package com.qiangu.ntd.model.response;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * <用户实体>
 *
 * @author fuxj
 * @data: 15/11/10 下午7:38
 * @version: V1.0
 */
public class User extends BaseResponse implements Serializable {
    @SerializedName("token") public String token;
}
