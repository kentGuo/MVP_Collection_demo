package com.fg.mvp_collection_demo.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fg.mvp_collection_demo.R;

public enum CustomToast {
    INSTANCE;//实现单例
    private Toast mToast;
    private TextView mTvToast;

    public void showToast(Context ctx,String content)
    {
        if(mToast==null)
        {
            mToast=new Toast(ctx);
            mToast.setGravity(Gravity.CENTER,0,0);//设置toast显示的位置，这是居中
            mToast.setDuration(Toast.LENGTH_SHORT);//设置toast显示的时长
            View _root= LayoutInflater.from(ctx).inflate(R.layout.toast_custom_common,null);//自定义样式，自定义布局文件
            mTvToast=(TextView)_root.findViewById(R.id.tvCustomToast);
            mToast.setView(_root);//设置自定义的view
        }
        mTvToast.setText(content);//设置文本
        mToast.show();
    }
    public void showToast(Context ctx,int stringId)
    {
        showToast(ctx,ctx.getString(stringId));
    }
    public void cancelToast(){
        if(mToast!=null)
        {
            mToast.cancel();
            mToast=null;
            if(mTvToast!=null)
            {
                mTvToast=null;
            }
        }
    }
}