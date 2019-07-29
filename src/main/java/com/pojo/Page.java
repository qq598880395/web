package com.pojo;

import java.io.Serializable;

public class Page implements Serializable {
    private Integer page_id;
    private String page_src;
    private String page_name;
    private String page_status;

    public Integer getPage_id() {
        return page_id;
    }

    public void setPage_id(Integer page_id) {
        this.page_id = page_id;
    }

    public String getPage_src() {
        return page_src;
    }

    public void setPage_src(String page_src) {
        this.page_src = page_src;
    }

    public String getPage_name() {
        return page_name;
    }

    public void setPage_name(String page_name) {
        this.page_name = page_name;
    }

    public String getPage_status() {
        return page_status;
    }

    public void setPage_status(String page_status) {
        this.page_status = page_status;
    }
}
