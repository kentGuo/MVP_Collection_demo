package com.fg.mvp_collection_demo.util;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ScaleXSpan;

/**
 * 文字自适应工具
 */
public class TextJustification {
    public static SpannableStringBuilder justifyString(String str,int size)
    {
        SpannableStringBuilder spannableStringBuilder=new SpannableStringBuilder();
        if(TextUtils.isEmpty(str))return spannableStringBuilder;
        char[] chars=str.toCharArray();
        if(chars.length>=size||chars.length==1)
        {
            return spannableStringBuilder.append(str);
        }
        int l=chars.length;
        float scale=(float)(size-l)/(l-1);
        for(int i=0;i<l;i++)
        {
            spannableStringBuilder.append(chars[i]);
            if(i!=l-1)
            {
                SpannableString s=new SpannableString(" ");//全角空格
                s.setSpan(new ScaleXSpan(scale),0,1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                spannableStringBuilder.append(s);
            }
        }
        return spannableStringBuilder;
    }
}
