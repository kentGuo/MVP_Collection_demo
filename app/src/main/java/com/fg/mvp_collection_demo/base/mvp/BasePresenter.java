package com.fg.mvp_collection_demo.base.mvp;

public abstract class BasePresenter<T extends IView> implements IPresenter<T> {




    private T mMvpView;

    /**
     * getMvpView
     *
     * @return
     */
    protected T getMvpView() {
        return mMvpView;
    }

    /**
     * 关联 P 与 V
     *
     * @param view
     */
    @Override
    public void attachView(T view) {
        this.mMvpView = view;
    }

    /**
     * 取消关联P与V
     */
    @Override
    public void detachView() {
        mMvpView = null;
    }


}