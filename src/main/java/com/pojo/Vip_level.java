package com.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("vip_level")
public class Vip_level implements Serializable {
    @TableId(value = "level_id",type = IdType.AUTO)
    private int level_id;//积分等级id
    private String level_name;//等级名称
    private double level_need;//需要积分
    private double level_cost;//积分折扣
    private double next_need;//下一等级所需积分

    public double getNext_need() {
        return next_need;
    }

    public void setNext_need(double next_need) {
        this.next_need = next_need;
    }

    public int getLevel_id() {
        return level_id;
    }

    public void setLevel_id(int level_id) {
        this.level_id = level_id;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public double getLevel_need() {
        return level_need;
    }

    public void setLevel_need(double level_need) {
        this.level_need = level_need;
    }

    public double getLevel_cost() {
        return level_cost;
    }

    public void setLevel_cost(double level_cost) {
        this.level_cost = level_cost;
    }
}
