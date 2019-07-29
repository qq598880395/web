package com.pojo;

import java.io.Serializable;

public class Template implements Serializable {
    private Integer template_id;
    private String template_name;

    public Integer getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(Integer template_id) {
        this.template_id = template_id;
    }

    public String getTemplate_name() {
        return template_name;
    }

    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }
}
