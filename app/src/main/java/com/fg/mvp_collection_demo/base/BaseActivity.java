package com.fg.mvp_collection_demo.base;


import android.os.Bundle;

import androidx.annotation.Nullable;

import com.fg.mvp_collection_demo.application.MyApplication;
import com.fg.mvp_collection_demo.base.mvp.IView;
import com.fg.mvp_collection_demo.util.RxPermissionHelper;
import com.fg.mvp_collection_demo.util.ScreenAdaptation;
import com.fg.mvp_collection_demo.widget.CustomToast;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends RxFragmentActivity implements RxPermissionHelper.PermissionCallbacks, IView {
    protected Unbinder unbinder;//butterknife 绑定控件

    /***
     * 获取布局Id
     * @return
     */
    protected abstract int getContentViewId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenAdaptation.setCustomDensity(this,getApplication());//修改屏幕密度
        setContentView(getContentViewId());
        unbinder= ButterKnife.bind(this);//butterkNife绑定view
        if(useEventBus()){
            EventBus.getDefault().register(this);//注册eventBus
        }
    }

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 是否使用eventBus
     * @return
     */
    protected boolean useEventBus(){
        return false;
    }

    /**
     * 动态申请权限成功回调
     */
    @Override
    public void onPermissionGranted() {

    }

    /**
     * 动态申请权限失败回调
     */
    @Override
    public void onPermissionDenied() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    /**
     * bind rxlifecycle
     * @param <T>
     * @return
     */
    @Override
    public <T> LifecycleTransformer<T> bindLifecycle() {
        return this.bindUntilEvent(ActivityEvent.PAUSE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder!=null)
        {
            unbinder.unbind();//butterKnife解绑view
        }
        if(useEventBus())
        {
            if(EventBus.getDefault().isRegistered(this))
            {
                EventBus.getDefault().unregister(this);//注销eventBus
            }
        }
        CustomToast.INSTANCE.cancelToast();//销毁自定义Toast
        MyApplication.getRefWatcher(this).watch(this);//内存泄漏检测
    }

    /**
     * 统一封装toast
     * @param text
     */
    protected void showToast(String text)
    {
        CustomToast.INSTANCE.showToast(this,text);
    }
}
