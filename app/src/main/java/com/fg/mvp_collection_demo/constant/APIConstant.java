package com.fg.mvp_collection_demo.constant;

import okhttp3.MediaType;

public class APIConstant {
    public final static String BASE_PATH = "http://www.ubusclub.com:2090/Member/";

    public static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");


    //登录
    public final static String LOGIN = "Login";
    //获取用户信息
    public final static String GET_USER_INFO = "GetMemberInfo";


}
