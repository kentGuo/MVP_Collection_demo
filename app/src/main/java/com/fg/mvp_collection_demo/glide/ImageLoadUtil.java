package com.fg.mvp_collection_demo.glide;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

public class ImageLoadUtil {
    /**
     * 加载图片
     * @param context
     * @param imageView
     * @param url
     * @param emptyImg
     */
    public static void loadImage(Context context, ImageView imageView,String url,int emptyImg)
    {
        if(!TextUtils.isEmpty(url))
        {
            GlideApp.with(context).load(url).placeholder(emptyImg).error(emptyImg).into(imageView);
        }else{
            loadImage(context,imageView,emptyImg,emptyImg);
        }
    }

    /**
     * 加载圆角图片
     * @param context
     * @param imageView
     * @param url
     * @param emptyImg
     */
    public static  void loadRoundImage(Context context,ImageView imageView,String url,int emptyImg)
    {
        if(!TextUtils.isEmpty(url))
        {
            GlideApp.with(context).load(url).placeholder(emptyImg).error(emptyImg).transform(new RoundedCorners(20)).into(imageView);
        }else{
            loadRoundImage(context,imageView,emptyImg,emptyImg);
        }
    }

    /**
     * 加载圆形图片
     * @param context
     * @param imageView
     * @param url
     * @param emptyImg
     */
    public static void loadCircleImage(Context context,ImageView imageView,String url,int emptyImg)
    {
        if(!TextUtils.isEmpty(url))
        {
            GlideApp.with(context).load(url).placeholder(emptyImg).error(emptyImg).transform(new CircleCrop()).into(imageView);
        }else{
            loadCircleImage(context,imageView,emptyImg,emptyImg);
        }
    }
    /**
     * 加载drawable中的图片资源
     * @param context
     * @param imageView
     * @param resId
     * @param emptyImg
     */
    public static void loadImage(Context context, ImageView imageView,int resId,int emptyImg)
    {
        GlideApp.with(context).load(resId).placeholder(emptyImg).into(imageView);
    }

    /**
     * 加载drawable中的图片资源 圆角
     * @param context
     * @param imageView
     * @param resId
     * @param emptyImg
     */
    public static  void loadRoundImage(Context context,ImageView imageView,int resId,int emptyImg)
    {
        GlideApp.with(context).load(emptyImg).placeholder(emptyImg).transform(new RoundedCorners(20)).into(imageView);
    }

    /**
     * 加载drawable中的图片资源 圆形
     * @param context
     * @param imageView
     * @param resId
     * @param emptyImg
     */
    public static void loadCircleImage(Context context,ImageView imageView,int resId,int emptyImg)
    {
        GlideApp.with(context).load(emptyImg).placeholder(emptyImg).transform(new CircleCrop()).into(imageView);
    }
}