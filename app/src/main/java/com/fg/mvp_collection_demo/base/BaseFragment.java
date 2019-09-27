package com.fg.mvp_collection_demo.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    protected Activity mActivity;
    private View rootView;
    protected Unbinder unbinder;//butterknife 绑定控件

    /**
     * 创建Fragment
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=getActivity();//获取当前fragment所依赖的Activity
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder= ButterKnife.bind(this,view);
        if(useEventbus())
        {
            if(!EventBus.getDefault().isRegistered(this)){
                EventBus.getDefault().register(this);//注册eventBus
            }
        }
        initView();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder!=null)
        {
            unbinder.unbind();
        }
        if(useEventbus())
        {
            if(EventBus.getDefault().isRegistered(this))
            {
                EventBus.getDefault().unregister(this);//注销eventBus
            }
        }
    }

    /**
     * 是否使用eventBus
     * @return
     */
    protected boolean useEventbus(){
        return false;
    }
    //获取layoutID，必须由子类实现
    public abstract int getContentLayoutId();
    //初始化布局，必须由子类实现
    protected abstract void initView();
    //初始化数据，必须由子类实现
    protected abstract void initData();
}