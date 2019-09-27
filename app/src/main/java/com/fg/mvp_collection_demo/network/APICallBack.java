package com.fg.mvp_collection_demo.network;

import com.fg.mvp_collection_demo.base.BaseResponse;
import com.fg.mvp_collection_demo.util.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class APICallBack<M> implements Observer<M> {
    private static final String TAG=APICallBack.class.getSimpleName();
    public abstract void onStart(Disposable d);
    public abstract void onSuccess(M modelBean);
    public abstract void onFailure(String errorMsg);
    public abstract void onFinished();

    @Override
    public void onSubscribe(Disposable d) {
        onStart(d);
    }

    @Override
    public void onNext(M m) {
        Logger.d(TAG,m.toString());
        BaseResponse<M> response=(BaseResponse<M>)m;
        if("200".equals(response.getCode()))
        {
            onSuccess(m);
        }else{
            onFailure(response.getMessage());
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if(e instanceof HttpException)
        {
            HttpException httpException=(HttpException)e;
            int exceptionCode=httpException.code();
            String msg=httpException.getMessage();
            if(exceptionCode==401)
            {
                msg="用户名密码错误，请重新登录";
            }
            if(exceptionCode==403||exceptionCode==404||exceptionCode==407||exceptionCode==408)
            {
                msg="网络链接超时，请稍后再试";
            }
            if(exceptionCode==501||exceptionCode==502||exceptionCode==504)
            {
                msg="服务器无响应，请稍后再试";
            }
            onFailure(msg);

        }else{
            onFailure(e.getMessage());
        }
        onFinished();
    }

    @Override
    public void onComplete() {
        onFinished();
    }
}