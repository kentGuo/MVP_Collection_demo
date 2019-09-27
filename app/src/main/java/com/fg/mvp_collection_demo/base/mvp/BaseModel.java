package com.fg.mvp_collection_demo.base.mvp;

import android.text.TextUtils;

import com.fg.mvp_collection_demo.application.MyApplication;
import com.fg.mvp_collection_demo.network.RxService;
import com.fg.mvp_collection_demo.util.Logger;
import com.fg.mvp_collection_demo.util.UserUtil;
import com.fg.mvp_collection_demo.widget.CustomToast;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseModel {
    private static final String TAG=BaseModel.class.getSimpleName();

    /**
     * 返回服务接口对象实例
     * @param clazz
     * @param <T>
     * @return
     */
    protected <T> T createService(final Class<T> clazz)
    {
        return RxService.RETROFIT.createService(clazz);
    }

    /**
     * 获取公共请求参数
     * @return
     */
    protected Map<String ,String> createBaseParamMap(){
//        if(!TextUtils.isEmpty(UserUtil.getInstance().getUserId()))
//        {
//            CustomToast.INSTANCE.showToast(MyApplication.getContext(),"用户信息为空");
//            Logger.d(TAG,"用户信息为空");
//            throw new NullPointerException("用户id为空");
//            //应该跳转登录页，重新登录
//        }
//        if(!TextUtils.isEmpty(UserUtil.getInstance().getMobile()))
//        {
//            throw new NullPointerException("用户账户为空");
//            //应该跳转登录页，重新登录
//        }
        Map<String,String> map=new HashMap<>();
        map.put("UserId", "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456");//用户id
        map.put("UserName", "18856422586");
        map.put("DeviceType", "2");//设备类型:android / ios
        return map;
    }
}