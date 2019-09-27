package com.fg.mvp_collection_demo.base;

public abstract class BaseEntity {
    protected String Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}