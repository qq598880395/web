package com.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dao.ImgDAO;
import com.pojo.Img;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImgService {

    @Autowired
    private ImgDAO imgDAO;

    //添加图片
    public int addImage(String img_name,String newFilePath,Integer tmpid,String img_href){
        Img img=new Img();
        img.setImg_name(img_name);
        img.setImg_src(newFilePath);
        img.setTemplate_id(tmpid);
        if(img_href!=null){
            img.setImg_href(img_href);
        }else img.setImg_href("#");
        img.setImg_status("yes");
        int n=imgDAO.insert(img);
        return n;
    }

    //查询当前模块图片
    public List<Img> searchImgById(Integer tmpid, String imgStatus,String img_src){
        QueryWrapper qw =new QueryWrapper();
        qw.eq("template_id",tmpid);
        qw.eq("img_status",imgStatus);
        qw.eq("img_src",img_src);
        List<Img> imgList= imgDAO.selectList(qw);
        return imgList;
    }

    //查询轮播所有图片
    public IPage searchImgById(Integer tmpid, int page,int limit) {
        QueryWrapper qw =new QueryWrapper();
        qw.eq("template_id",tmpid);
        qw.orderByDesc("img_status");
        Page p = new Page(page,limit);
        IPage<Img> imgList= imgDAO.selectPage(p,qw);
        return imgList;
    }

    //根据id删除图片
    public void delImgById(String img_id) {
        QueryWrapper qw =new QueryWrapper();
        qw.eq("img_id",img_id);
        imgDAO.delete(qw);
    }

    //根据id更新链接
    public void updataImg_href(String img_href, String img_id) {
        UpdateWrapper uw = new UpdateWrapper();
        Img img = new Img();
        uw.eq("img_id",img_id);
        img.setImg_href(img_href);
        imgDAO.update(img,uw);
    }

    //根据id修改图片状态
    public void updataImg_status(String img_status, String img_id) {
        UpdateWrapper uw = new UpdateWrapper();
        Img img = new Img();
        uw.eq("img_id",img_id);
        img.setImg_status(img_status);
        imgDAO.update(img,uw);
    }

    public List<Img> searchImgsByTemplate_id(int template_id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("template_id",template_id);
        List<Img> img_list = imgDAO.selectList(queryWrapper);
        return img_list;
    }
}
