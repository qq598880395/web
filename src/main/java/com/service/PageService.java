package com.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dao.PageDAO;
import com.pojo.Page;
import com.vo.PageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageService {
    @Autowired
    private PageDAO pageDAO;

    //添加页面模板
    public int addPage(PageVO vo){
        Page po = new Page();
        BeanUtils.copyProperties(vo,po);
        int n = pageDAO.insert(po);
        return n;
    }

    //查询当前微官网页面
    public Page searchNowPage(){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("page_status","yes");
        Page result = pageDAO.selectOne(queryWrapper);
        return result;
    }

    //更新微官网主页面
    public int updataPage(String page_name){
        UpdateWrapper updateWrapper = new UpdateWrapper();
        UpdateWrapper updateWrapper2 = new UpdateWrapper();

        updateWrapper.eq("page_status","yes");
        Page page = new Page();
        page.setPage_status("no");
        pageDAO.update(page,updateWrapper);
        updateWrapper2.eq("page_name",page_name);
        page.setPage_status("yes");
        int n = pageDAO.update(page,updateWrapper2);
        return n;
    }

}
