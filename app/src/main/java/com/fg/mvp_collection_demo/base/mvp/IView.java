package com.fg.mvp_collection_demo.base.mvp;

import com.trello.rxlifecycle2.LifecycleTransformer;

public interface IView {
    <T> LifecycleTransformer<T> bindLifecycle();

    void showLoading();

    void hideLoading();
}