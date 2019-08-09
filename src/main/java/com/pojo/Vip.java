package com.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("vip_list")
public class Vip implements Serializable {
    @TableId(value="vip_id",type = IdType.AUTO)
    private String vip_id;  //会员号

    private int openid;  //微信openid
    private String vip_tel; //会员电话
    private String vip_name;//会员姓名
    private String vip_IDcard; //会员身份证
    private double vip_money; //会员余额
    private String vip_time;//会员加入时间
    private String hotel_name;//酒店名
    private  int hotel_id;//酒店id
    private int level_id;//积分等级ID
    private double level_num;//积分数
    public String getVip_time() {
        return vip_time;
    }

    public int getLevel_id() {
        return level_id;
    }

    public void setLevel_id(int level_id) {
        this.level_id = level_id;
    }

    public double getLevel_num() {
        return level_num;
    }

    public void setLevel_num(double level_num) {
        this.level_num = level_num;
    }

    public void setVip_time(String vip_time) {
        this.vip_time = vip_time;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
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



    public int getOpenid() {
        return openid;
    }

    public void setOpenid(int openid) {
        this.openid = openid;
    }

    public String getVip_tel() {
        return vip_tel;
    }

    public void setVip_tel(String vip_tel) {
        this.vip_tel = vip_tel;
    }

    public String getVip_name() {
        return vip_name;
    }

    public void setVip_name(String vip_name) {
        this.vip_name = vip_name;
    }

    public String getVip_IDcard() {
        return vip_IDcard;
    }

    public void setVip_IDcard(String vip_IDcard) {
        this.vip_IDcard = vip_IDcard;
    }

    public double getVip_money() {
        return vip_money;
    }

    public void setVip_money(double vip_money) {
        this.vip_money = vip_money;
    }




}
