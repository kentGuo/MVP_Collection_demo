package com.fg.mvp_collection_demo.base.mvp;

public interface IPresenter <T extends IView>{
    /**
     * 关联p与v
     * @param View
     */
    void attachView(T View);

    /**
     * 取消关联p与v
     */
    void detachView();
}