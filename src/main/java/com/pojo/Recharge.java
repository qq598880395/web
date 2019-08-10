package com.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("recharge")
public class Recharge implements Serializable {
    @TableId(value = "rc_id",type = IdType.AUTO)
    private String rc_id;//充值id
    private int hotel_id;//酒店id
    private String vip_id;//会员id
    private double rc_cost;//充值金额
    private String rc_time;//订单时间
    private String vip_name;//会员姓名
    private String vip_tel;//会员电话
    private double rc_cost_1st;//实际支付金额

    public double getRc_cost_1st() {
        return rc_cost_1st;
    }

    public void setRc_cost_1st(double rc_cost_1st) {
        this.rc_cost_1st = rc_cost_1st;
    }

    public String getVip_name() {
        return vip_name;
    }

    public void setVip_name(String vip_name) {
        this.vip_name = vip_name;
    }

    public String getVip_tel() {
        return vip_tel;
    }

    public void setVip_tel(String vip_tel) {
        this.vip_tel = vip_tel;
    }

    public String getRc_id() {
        return rc_id;
    }

    public void setRc_id(String rc_id) {
        this.rc_id = rc_id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getVip_id() {
        return vip_id;
    }

    public void setVip_id(String vip_id) {
        this.vip_id = vip_id;
    }

    public double getRc_cost() {
        return rc_cost;
    }

    public void setRc_cost(double rc_cost) {
        this.rc_cost = rc_cost;
    }

    public String getRc_time() {
        return rc_time;
    }

    public void setRc_time(String rc_time) {
        this.rc_time = rc_time;
    }
}
