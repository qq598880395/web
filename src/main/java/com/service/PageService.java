package com.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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

    //查询历史模板
    public IPage<Page> searchHistoryHtml(int page, int limit) {
        QueryWrapper qw =new QueryWrapper();
        qw.orderByDesc("create_time");
        com.baomidou.mybatisplus.extension.plugins.pagination.Page p = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(page,limit);
        IPage<Page> pageList= pageDAO.selectPage(p,qw);
        return pageList;
    }

    //根据Id删除页面
    public void delPageById(String page_id) {
        QueryWrapper qw =new QueryWrapper();
        qw.eq("page_id",page_id);
        pageDAO.delete(qw);
    }

    //判断页面是否存在
    public String pageitExist(String value, String column) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq(column,value);
        int n = pageDAO.selectCount(qw);
        if (n>=1){
            return "true";
        }
        return "false";
    }

    //根据页面名查询路径
    public String selectPage_src(String page_name) {
        String src = pageDAO.getSrc(page_name);
        return src;
    }

    //更新覆盖原有页面
    public void coverPage(PageVO vo, String page_name) {
        Page po = new Page();
        BeanUtils.copyProperties(vo,po);
        UpdateWrapper uw = new UpdateWrapper();
        uw.eq("page_name",page_name);
        pageDAO.update(po,uw);
    }

    public void updataPage_status(String page_id) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        UpdateWrapper updateWrapper2 = new UpdateWrapper();
        updateWrapper.eq("page_status","yes");
        Page page = new Page();
        page.setPage_status("no");
        pageDAO.update(page,updateWrapper);
        System.out.println(page_id);
        updateWrapper2.eq("page_id",page_id);
        page.setPage_status("yes");
        pageDAO.update(page,updateWrapper2);
    }

    public int delMostPage(JSONArray pageJson) {
        int n = 0;
        JSONArray jsonArray = null;
        jsonArray = new JSONArray(pageJson);
        for (int i=0;i<jsonArray.size();i++){
            int page_id = (int) jsonArray.getJSONObject(i).get("page_id");
            n = pageDAO.deleteById(page_id);
        }
        return n;
    }
}
