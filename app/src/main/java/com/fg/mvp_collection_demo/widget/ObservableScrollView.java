package com.fg.mvp_collection_demo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 自定义监听滑动的ScrollView
 */
public class ObservableScrollView extends ScrollView {
    private ScrollViewListener scrollViewListener=null;

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(scrollViewListener!=null)
        {
            if(oldt<t)
            {
                //手指向上滑动，屏幕内容下滑
                scrollViewListener.onScroll(oldt,t,false);
            }else if(oldt>t)
            {
                //手指向下滑动，屏幕内容上滑
                scrollViewListener.onScroll(oldt,t,true);
            }
        }
    }

    public interface ScrollViewListener{
        //dy Y轴滑动距离，isUP是否返回顶部
        void onScroll(int oldy,int dy,boolean isUp);
    }
}
