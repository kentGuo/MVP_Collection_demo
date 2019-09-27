package com.fg.mvp_collection_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fg.mvp_collection_demo.bean.UserInfo;
import com.fg.mvp_collection_demo.util.Logger;
import com.fg.mvp_collection_demo.util.UserUtil;

public class UserTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserInfo userInfo= UserUtil.getInstance().get();
        if(userInfo!=null)
        {
            Logger.d("userInfo111",userInfo.toString());
        }else{
            Logger.d("userInfo111","塔变空了");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
