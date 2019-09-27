package com.fg.mvp_collection_demo.network;

import com.fg.mvp_collection_demo.base.BaseResponse;
import com.fg.mvp_collection_demo.bean.UserInfo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {
    @POST("{login}")
    Observable<BaseResponse> login(@Path("login") String url, @Body Map<String,String> params);

    @POST("{getUserinfo}")
    Observable<BaseResponse<UserInfo>> getUserInfo(@Path("getUserinfo") String url, @Body Map<String,String> params);
}
