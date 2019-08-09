package com.dao;

import com.pojo.Recharge;
import com.util.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RechargeDAO  {
    public int addOrder(@Param("vip_id") String vip_id, @Param("rc_id") String rc_id, @Param("rc_cost") double rc_cost, @Param("hotel_id") int hotel_id, @Param("vip_name") String vip_name, @Param("vip_tel") String vip_tel, @Param("rc_cost_1st") double rc_cost_1st);
    //通过关键字分页查询数据列表
    public List<Recharge> selectPageList(Page page);

    //通过关键字分页查询，返回总记录数
    public Integer selectPageCount(Page page);
}
