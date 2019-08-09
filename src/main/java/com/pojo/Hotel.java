package com.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("hotel")
public class Hotel implements Serializable {
    @TableId(value = "hotel_id" ,type = IdType.AUTO)
    private int hotel_id;//酒店ID
    private String hotel_area;//酒店地区
    private String hotel_name;//酒店名
    private String hotel_details;//酒店介绍

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel_area() {
        return hotel_area;
    }

    public void setHotel_area(String hotel_area) {
        this.hotel_area = hotel_area;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_details() {
        return hotel_details;
    }

    public void setHotel_details(String hotel_details) {
        this.hotel_details = hotel_details;
    }
}
