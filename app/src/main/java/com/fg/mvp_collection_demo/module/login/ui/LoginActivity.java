package com.fg.mvp_collection_demo.module.login.ui;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fg.mvp_collection_demo.R;
import com.fg.mvp_collection_demo.base.BaseActivity;
import com.fg.mvp_collection_demo.bean.UserInfo;
import com.fg.mvp_collection_demo.module.login.contract.LoginContract;
import com.fg.mvp_collection_demo.module.login.presenter.LoginPresenter;
import com.fg.mvp_collection_demo.util.Logger;
import com.fg.mvp_collection_demo.util.RxPermissionHelper;

import java.io.File;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.Luban;

public class LoginActivity extends BaseActivity implements LoginContract.IViewVontract {
    private static final String TAG=LoginActivity.class.getSimpleName();
    @BindView(R.id.layout_login)
    LinearLayout ll_login;
    private LoginPresenter mPresenter;
    private Dialog mLoadingDialog;//loadingDialog
    /***
     * 获取布局Id
     * @return
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=new LoginPresenter();
        mPresenter.attachView(this);//关联p与v
        ll_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        UserInfo userInfo=new UserInfo();
        userInfo.setId("11111");
        userInfo.setNickName("李淳罡");
        userInfo.setChecked(true);
        Log.d("userInfo",userInfo.toString());
    }
    private void init(){
        UserInfo userInfo=new UserInfo();

    }
    /**
     * 初始化view
     */
    @Override
    protected void initView() {
            ll_login=((LinearLayout)findViewById(R.id.layout_login));
            //没有数据时点击屏幕刷新数据
        ll_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.login();
            }
        });
        String[] permissions=new String[]{Manifest.permission.INTERNET,Manifest.permission.VIBRATE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        RxPermissionHelper.newInstance(this).requestPermissions(this,permissions);
    }
    private void CompressImage(String path)
    {
        Observable.just(path)//批量压缩，建议使用Flowable，下游处理图片有可能会导致压背的问题
        .observeOn(Schedulers.io()).map(new Function<String, File>() {
            @Override
            public File apply(String s) throws Exception {
                return Luban.with(LoginActivity.this).get(s);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<File>() {
            @Override
            public void accept(File file) throws Exception {
                //显示图片
            }
        });
    }
    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        Observable.intervalRange(1,30,10,1, TimeUnit.SECONDS).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                Logger.d(TAG,"onNext:"+(20-aLong));
            }

            @Override
            public void onError(Throwable e) {
                Logger.d(TAG,"onError"+e);
            }

            @Override
            public void onComplete() {
                Logger.d(TAG,"onComplete");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();//取消关联p与v
        super.onDestroy();
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
        mPresenter.getUserInfo();//获取用户信息
    }

    @Override
    public void loginFailure() {
        Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show();
        ll_login.setVisibility(View.GONE);
    }

    @Override
    public void getUserInfoSuccess() {
        Toast.makeText(this,"获取用户信息成功",Toast.LENGTH_SHORT).show();
        ll_login.setVisibility(View.VISIBLE);
    }

    @Override
    public void getUserInfoFailure() {
        ll_login.setVisibility(View.GONE);
    }
    private static float sNoncompatDensity;
    private static float sNoncompatScaledDensity;
    private static void setCustomDensity(@NonNull Activity activity, @NonNull final Application application) {

        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
    }
}
