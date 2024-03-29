package com.fg.mvp_collection_demo.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.appbar.AppBarLayout;

/**
 * 滚动冲突
 */
public class ScrollConflict {

    /**
     * swipeRefreshLayout滚动冲突
     * @param swipeRefreshLayout
     * @param view
     */
    public static void swipeRefreshLauoutConflict(final SwipeRefreshLayout swipeRefreshLayout, View view)
    {
        /**
         * 与listview冲突
         */
        if(view instanceof ListView)
        {
            final ListView listView=(ListView)view;
            listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {

                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    boolean enable=false;
                    if(listView!=null&&listView.getChildCount()>0)
                    {
                        //check if the first item of the list is visible
                        boolean firstItemVisible=listView.getFirstVisiblePosition()==0;
                        //check if the top if the first is visible
                        boolean topOfFirstItemVisible=listView.getChildAt(0).getTop()==0;
                        //enabling or disabling the refresh layout
                        enable=firstItemVisible&&topOfFirstItemVisible;
                    }
                    swipeRefreshLayout.setEnabled(enable);
                }
            });
        }
        /**
         * 与CoordinatorLayout冲突
         */
        else if(view instanceof AppBarLayout)
        {
            AppBarLayout appBarLayout=(AppBarLayout)view;
            appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                    if(i>=0)
                    {
                        swipeRefreshLayout.setEnabled(true);
                    }else{
                        swipeRefreshLayout.setEnabled(false);
                    }
                }
            });
        }
    }

    /**
     * listview与ScrollView滚动冲突
     * 重新计算ListView的高度，解决ScrollView和ListView两个View都有滚动的效果
     * 在嵌套使用时起冲突的问题
     * @param listView
     */
    public static void setListViewHeight(ListView listView)
    {
        //获取ListView对应的Adapter
        ListAdapter listAdapter=listView.getAdapter();
        if(listAdapter==null)return;
        int toTalHeight=0;
        for(int i=0,len=listAdapter.getCount();i<len;i++)
        {
            //listAdapter.getCount()返回数据项的数目
            View listItem=listAdapter.getView(i,null,listView);
            listItem.measure(0,0);//计算子项View 的宽高
            toTalHeight+=listItem.getMeasuredHeight();//统计所有子项的总高度
        }
        ViewGroup.LayoutParams params=listView.getLayoutParams();
        params.height=toTalHeight+(listView.getDividerHeight()*(listAdapter.getCount()-1));
        listView.setLayoutParams(params);
    }

    /**
     * gridView 与 ScrollView 滚动冲突
     * @param adapter
     * @param gridView
     */
    public static void setGridViewHeighth(BaseAdapter adapter, GridView gridView)
    {
        //固定列宽，有多少列
        int col=2;
        int totalHeight=0;
        //i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度
        //listAdapter.getCount()小鱼等于5时计算两次高度相加
        for(int i=0;i<adapter.getCount();i+=col)
        {
            //获取listview的每一个item
            View listItem=adapter.getView(i,null,gridView);
            listItem.measure(0,0);
            //获取item的高度和
            totalHeight+=listItem.getMeasuredHeight();
        }
        //获取listview的布局参数
        ViewGroup.LayoutParams params=gridView.getLayoutParams();
        //设置高度
        params.height=totalHeight;
        //设置margin
        ((ViewGroup.MarginLayoutParams)params).setMargins(10,10,10,10);
        //设置参数
        gridView.setLayoutParams(params);
    }
}