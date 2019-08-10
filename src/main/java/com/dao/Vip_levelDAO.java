package com.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pojo.Vip_level;

public interface Vip_levelDAO extends  BaseMapper<Vip_level> {
    public Vip_level findbylevel_id(int level_id);

}