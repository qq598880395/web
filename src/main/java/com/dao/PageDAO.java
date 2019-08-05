package com.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pojo.Page;

public interface PageDAO extends BaseMapper<Page> {

    public String getSrc(String page_name);
}
