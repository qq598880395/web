package com.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.sql.Timestamp;

@TableName("page")
public class Page implements Serializable {
    @TableId(type= IdType.AUTO)
    private Integer page_id;

    @TableField
    private String page_name;
    private String page_src;
    private String page_status;
    private Timestamp create_time;

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Integer getPage_id() {
        return page_id;
    }

    public void setPage_id(Integer page_id) {
        this.page_id = page_id;
    }

    public String getPage_name() {
        return page_name;
    }

    public void setPage_name(String page_name) {
        this.page_name = page_name;
    }

    public String getPage_src() {
        return page_src;
    }

    public void setPage_src(String page_src) {
        this.page_src = page_src;
    }

    public String getPage_status() {
        return page_status;
    }

    public void setPage_status(String page_status) {
        this.page_status = page_status;
    }
}
