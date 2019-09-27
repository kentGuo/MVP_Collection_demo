package com.fg.mvp_collection_demo.util;

import android.app.Activity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * 动态权限申请
 */
public class RxPermissionHelper {
    private PermissionCallbacks listener;
    private RxPermissionHelper(PermissionCallbacks listener)
    {
        this.listener=listener;
    }
    public static RxPermissionHelper newInstance(PermissionCallbacks listener){
        return new RxPermissionHelper(listener);
    }

    /**
     * 申请权限
     * @param activity
     * @param permission
     */
    public void requestPermissions(Activity activity,String...permission){
        RxPermissions rxPermissions=new RxPermissions(activity);
        rxPermissions.request(permission).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if(aBoolean)
                {
                    listener.onPermissionGranted();
                }else {
                    listener.onPermissionDenied();
                }
            }
        });
    }
    /**
     申请权限接口回调
     */
    public interface PermissionCallbacks{
        //同意授权
        void onPermissionGranted();
        //拒绝授权
        void onPermissionDenied();
    }
}