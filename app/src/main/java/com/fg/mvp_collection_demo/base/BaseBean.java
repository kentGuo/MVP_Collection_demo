package com.fg.mvp_collection_demo.base;

import java.io.Serializable;

import lombok.Data;

@Data
public class BaseBean implements Serializable {
    protected String Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}