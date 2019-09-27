package com.fg.mvp_collection_demo.base;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
    private String Code;
    private String Message;
    private T Content;

    public String getCode() {
        return Code;
    }

    public String getMessage() {
        return Message;
    }

    public T getContent() {
        return Content;
    }

    @Override
    public String toString() {
        return "BaseResponse{"+"Code="+Code+'\''+",Message="+Message+'\''+",Content="+Content+'}';
    }
}