package com.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
        img.setImg_href(img_href);
        img.setImg_status("yes");
        int n=imgDAO.insert(img);
        return n;
    }

    //查询当前模块图片
    public List<Img> searchImgById(Integer tmpid, String imgStatus){
        QueryWrapper qw =new QueryWrapper();
        qw.eq("template_id",tmpid);
        qw.eq("img_status",imgStatus);
        List<Img> imgList= imgDAO.selectList(qw);
        return imgList;
    }

    //查询轮播所有图片
    public List<Img> searchImgById(Integer tmpid) {
        QueryWrapper qw =new QueryWrapper();
        qw.eq("template_id",tmpid);
        List<Img> imgList= imgDAO.selectList(qw);
        return imgList;
    }

    public void delImgById(String img_id) {
        QueryWrapper qw =new QueryWrapper();
        qw.eq("img_id",img_id);
        imgDAO.delete(qw);
    }

    public void updataImg_href(String img_href, String img_id) {
        UpdateWrapper uw = new UpdateWrapper();
        Img img = new Img();
        uw.set("img_id",img_id);
        img.setImg_href(img_href);
        imgDAO.update(img,uw);
    }
}
