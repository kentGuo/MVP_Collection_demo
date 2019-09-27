package com.fg.mvp_collection_demo.module.login.contract;

import com.fg.mvp_collection_demo.base.BaseResponse;
import com.fg.mvp_collection_demo.base.mvp.IView;
import com.fg.mvp_collection_demo.bean.UserInfo;

import io.reactivex.Observable;

public interface LoginContract {
    interface IPresenterContract{
        void login();
        void getUserInfo();
    }
    interface IViewVontract extends IView{
        void loginSuccess();
        void loginFailure();
        void getUserInfoSuccess();
        void getUserInfoFailure();
    }
    interface IModelContract{
        Observable<BaseResponse> login(String code,String psd);
        Observable<BaseResponse<UserInfo>> getUserInfo();
    }
}
