package com.fg.mvp_collection_demo.network;

import com.fg.mvp_collection_demo.constant.APIConstant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public enum RxService {
    RETROFIT;
    private Retrofit mRetrofit;
    private static final int READ_TIMEOUT=60;//读取超时时间，单位秒
    private static final int CONN_TIMEOUT=50;//连接超时时间，单位秒

    /**
     * 初始化一个client，不然retrofit会自己默认添加一个
     */
    private static OkHttpClient httpClient=new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder=chain.request().newBuilder();
            builder.addHeader("Content-Type","application/json");
            return chain.proceed(builder.build());
        }
    }).connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS)//设置连接时间为50s
            .readTimeout(READ_TIMEOUT,TimeUnit.SECONDS)//设置读取时间为1分钟
            .build();
    /**
     * 创建Retrofit对象
     * @return
     */
    public Retrofit createRetrifit() {
        if (mRetrofit == null){
            mRetrofit = new Retrofit.Builder().client(httpClient)
                    .baseUrl(APIConstant.BASE_PATH)//api base path
                    .addConverterFactory(GsonConverterFactory.create())//返回值为Gson的支持（以实体类返回）
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//返回值为Observable<T>的支持
                    .build();
        }
        return mRetrofit;
    }

    /**
     * 返回服务接口对象实例
     * @param service
     * @param <T>
     * @return
     */
    public <T> T createService(final  Class<T> service)
    {
        validateServiceInterface(service);//检验接口合法性
        return RxService.RETROFIT.createRetrifit().create(service);
    }
    /**
     * @deprecated 校验接口合法性
     * @param service
     * @param <T>
     */
    public <T> void validateServiceInterface(Class<T> service)
    {
        if(service==null)
        {}
        if(!service.isInterface())
        {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        }
        if(service.getInterfaces().length>0)
        {
            throw new IllegalArgumentException("API interfaces must not extend other interfaces");
        }
    }
}