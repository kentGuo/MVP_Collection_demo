package com.fg.mvp_collection_demo.base;

import androidx.annotation.NonNull;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * 带刷新功能的Fragment
 * 实现了下拉刷新和上拉加载更多 功能
 */
public abstract class SmartRefreshFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {

    /**
     * 下拉刷新
     * @param refreshLayout
     */
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }

    /**
     * 下拉刷新
     * @param refreshLayout
     */
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }
}