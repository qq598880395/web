package com.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("rc_case")
public class Rc_case implements Serializable {
    @TableId(value="rc_caseid",type = IdType.AUTO)
    private int rc_caseid;
    private int rc_a;
    private int rc_b;
    private int rc_c;
    private int rc_a_regiv;
    private int rc_b_regiv;
    private int rc_c_regiv;

    public int getRc_caseid() {
        return rc_caseid;
    }

    public void setRc_caseid(int rc_caseid) {
        this.rc_caseid = rc_caseid;
    }

    public int getRc_a() {
        return rc_a;
    }

    public void setRc_a(int rc_a) {
        this.rc_a = rc_a;
    }

    public int getRc_b() {
        return rc_b;
    }

    public void setRc_b(int rc_b) {
        this.rc_b = rc_b;
    }

    public int getRc_c() {
        return rc_c;
    }

    public void setRc_c(int rc_c) {
        this.rc_c = rc_c;
    }

    public int getRc_a_regiv() {
        return rc_a_regiv;
    }

    public void setRc_a_regiv(int rc_a_regiv) {
        this.rc_a_regiv = rc_a_regiv;
    }

    public int getRc_b_regiv() {
        return rc_b_regiv;
    }

    public void setRc_b_regiv(int rc_b_regiv) {
        this.rc_b_regiv = rc_b_regiv;
    }

    public int getRc_c_regiv() {
        return rc_c_regiv;
    }

    public void setRc_c_regiv(int rc_c_regiv) {
        this.rc_c_regiv = rc_c_regiv;
    }
}
