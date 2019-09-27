package com.fg.mvp_collection_demo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.SpannableString;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fg.mvp_collection_demo.glide.ImageLoadUtil;

public class CommonViewHolder {
    private SparseArray<View> mViews;
    private int mPosition;
    private View mConberView;
    private Context mContext;

    //构造函数
    public CommonViewHolder(Context context, ViewGroup parent, int lauoutID, int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<>();
        mContext = context;

        mConberView = LayoutInflater.from(context).inflate(lauoutID, parent, false);
        mConberView.setTag(this);
    }

    /**
     * viewholder的入口方法
     *
     * @param context
     * @param converView
     * @param parent
     * @param layoutID
     * @param position
     * @return
     */
    public static CommonViewHolder getViewHolder(Context context, View converView, ViewGroup parent, int layoutID, int position) {
        if (converView == null) {
            return new CommonViewHolder(context, parent, layoutID, position);
        } else {
            CommonViewHolder holder = ((CommonViewHolder) converView.getTag());
            holder.mPosition = position;
            return holder;
        }
    }

    public View getmConverView() {
        return this.mConberView;
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConberView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return ((T) view);
    }

    /**
     * 设置textView值
     *
     * @param viewId
     * @param text
     * @return
     */
    public CommonViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 给textview设置spannableString值
     *
     * @param viewId
     * @param sp
     * @return
     */
    public CommonViewHolder setText(int viewId, SpannableString sp) {
        TextView tv = getView(viewId);
        tv.setText(sp);
        return this;
    }

    /**
     * 设置字体颜色
     *
     * @param viewId
     * @param resId
     * @return
     */
    public CommonViewHolder setText(int viewId, int resId) {
        TextView tv = getView(viewId);
        tv.setTextColor(resId);
        return this;
    }

    /**
     * 设置空间
     * @param viewId
     * @param visible
     * @return
     */
    public CommonViewHolder setVisible(int viewId, int visible) {
        View v = getView(viewId);
        switch (visible) {
            case View.VISIBLE:
            case View.INVISIBLE:
            case View.GONE:
                v.setVisibility(visible);
                break;
            default:
                break;
        }
        return this;
    }

    /**
     * 给imageview设置bitmap
     * @param viewId
     * @param bitmap
     * @return
     */
    public CommonViewHolder setImageBitmap(int viewId, Bitmap bitmap)
    {
        ImageView iv=getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置imageView图片
     * @param viewId
     * @param res
     * @return
     */
    public CommonViewHolder setImageResource(int viewId,int res)
    {
        ImageView iv=getView(viewId);
        int r=0;//等待添加默认图片
        ImageLoadUtil.loadImage(mContext,iv,res,r);
        return this;
    }

    /**
     * 设置imageView图片
     * @param viewId
     * @param res
     * @return
     */
    public CommonViewHolder setImageResource(int viewId,String res)
    {
        ImageView iv=getView(viewId);
        int r=0;//等待添加默认图片
        ImageLoadUtil.loadImage(mContext,iv,res,r);
        return this;
    }

    /**
     * 设置圆角imageview图片
     * @param viewId
     * @param res
     * @return
     */
    public CommonViewHolder setImageRoundResource(int viewId,int res)
    {
        ImageView iv=getView(viewId);
        int r=0;//等待添加默认图片
        ImageLoadUtil.loadRoundImage(mContext,iv,res,r);
        return this;
    }

    /**
     * 设置圆角imageview图片
     * @param viewId
     * @param res
     * @return
     */
    public CommonViewHolder setImageRoundResource(int viewId,String res)
    {
        ImageView iv=getView(viewId);
        int r=0;//等待添加默认图片
        ImageLoadUtil.loadRoundImage(mContext,iv,res,r);
        return this;
    }

    /**
     * 设置背景颜色
     * @param viewId
     * @param color
     * @return
     */
    public CommonViewHolder setBackground(int viewId,int color)
    {
        View v=getView(viewId);
        int r=0;//等待添加默认图片
        v.setBackgroundColor(color);
        return this;
    }
}
