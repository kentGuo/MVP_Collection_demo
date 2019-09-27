package com.fg.mvp_collection_demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 通用适配器
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mDataes;
    protected LayoutInflater mInflater;
    private int mLayoutId;

    public CommonAdapter(Context mContext, List<T> mDataes, LayoutInflater mInflater, int mLayoutId) {
        this.mContext = mContext;
        this.mDataes = mDataes;
        this.mInflater = mInflater;
        this.mLayoutId = mLayoutId;
    }

    @Override
    public int getCount() {
        return mDataes.size();
    }

    @Override
    public T getItem(int position) {
        return mDataes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder holder=CommonViewHolder.getViewHolder(mContext,convertView,parent,mLayoutId,position);
        convert(holder,getItem(position));
        return holder.getmConverView();
    }

    /**
     * 需要暴露的方法
     * @param holder
     * @param t
     */
    public  abstract  void convert(CommonViewHolder holder,T t);
}
