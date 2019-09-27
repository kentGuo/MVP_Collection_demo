package com.fg.mvp_collection_demo.base;

import android.widget.BaseAdapter;

import androidx.annotation.NonNull;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public abstract class SmartRefreshActivity extends BaseAdapter  implements OnRefreshListener, OnLoadMoreListener {
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
