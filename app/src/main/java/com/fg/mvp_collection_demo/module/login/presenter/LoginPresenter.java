package com.fg.mvp_collection_demo.module.login.presenter;

import com.fg.mvp_collection_demo.base.BaseResponse;
import com.fg.mvp_collection_demo.base.mvp.BasePresenter;
import com.fg.mvp_collection_demo.bean.UserInfo;
import com.fg.mvp_collection_demo.module.login.contract.LoginContract;
import com.fg.mvp_collection_demo.module.login.module.LoginModel;
import com.fg.mvp_collection_demo.network.APICallBack;
import com.fg.mvp_collection_demo.network.RxTransformer;
import com.fg.mvp_collection_demo.util.Logger;

import io.reactivex.disposables.Disposable;

public class LoginPresenter extends BasePresenter<LoginContract.IViewVontract> implements LoginContract.IPresenterContract{
    private static final String TAG=LoginPresenter.class.getSimpleName();
    private LoginModel model;
    public LoginPresenter()
    {
        model=new LoginModel();
    }

    /**
     * login
     */
    @Override
    public void login() {
        model.login("13248365219","123456")
                .compose(RxTransformer.<BaseResponse>transformer(getMvpView())).subscribe(
                new APICallBack<BaseResponse>() {
                    @Override
                    public void onStart(Disposable d) {

                    }

                    @Override
                    public void onSuccess(BaseResponse modelBean) {
                            getMvpView().loginSuccess();
                        Logger.d(TAG,modelBean.getContent()+"");
                    }

                    @Override
                    public void onFailure(String errorMsg) {
                        getMvpView().loginFailure();
                    }

                    @Override
                    public void onFinished() {

                    }
                }
        );
    }

    @Override
    public void getUserInfo() {
    model.getUserInfo().compose(RxTransformer.<BaseResponse<UserInfo>>transformer(
            getMvpView()

    )).subscribe(new APICallBack<BaseResponse<UserInfo>>() {
        @Override
        public void onStart(Disposable d) {

        }

        @Override
        public void onSuccess(BaseResponse<UserInfo> modelBean) {
            getMvpView().getUserInfoSuccess();
            Logger.d(TAG,modelBean.getContent()+"");
        }

        @Override
        public void onFailure(String errorMsg) {
            getMvpView().getUserInfoFailure();
        }

        @Override
        public void onFinished() {
        getMvpView().hideLoading();
        }
    });
    }
}
