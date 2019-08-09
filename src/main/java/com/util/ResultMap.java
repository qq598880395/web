package com.util;

public class ResultMap<T> {
    private String msg;
    private  T data;
    private  int code;
    private  int count;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ResultMap(int code,String msg,  int count,T data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public ResultMap() {
    }

}
