package com.service;

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

}
