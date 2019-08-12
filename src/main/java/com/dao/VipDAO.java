package com.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pojo.Vip;
import com.util.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VipDAO extends  BaseMapper<Vip> {
    public Vip findbyVip_name(String vip_name);

    public Vip findbyVip_tel(@Param("vip_tel") String vip_tel, @Param("hotel_id") int hotel_id);

    public Vip findbyVip_id(String vip_id);

    public int addVipBytel(@Param("vip_id") String vip_id, @Param("tel") String tel);

    public int addVipByopenid(@Param("openid") int openid, @Param("vip_id") String vip_id);

    public int updateVipmsg(@Param("vip_id") String vip_id, @Param("vip_name") String name, @Param("vip_IDcard") String vip_IDcard, @Param("vip_tel") String vip_tel);

    public List findAll();

    public int count();

    public int deleteVip(String vip_id);

    public int countByVip_id(String vip_id);

    public int countByVip_tel(@Param("vip_tel") String vip_tel, @Param("hotel_id") int hotel_id);

    public int updateVip_money(@Param("vip_id") String vip_id, @Param("vip_money") double vip_money, @Param("level_id") int level_id, @Param("level_num") double level_num);

    //通过关键字分页查询数据列表
    public List<Vip> selectPageList(Page page);

    //通过关键字分页查询，返回总记录数
    public Integer selectPageCount(Page page);

}