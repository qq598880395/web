package com.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    //查询图片
    public List<Img> searchImgById(Integer tmpid, String imgStatus){
        QueryWrapper qw =new QueryWrapper();
        qw.eq("template_id",tmpid);
        qw.eq("img_status",imgStatus);
        List<Img> imgList= imgDAO.selectList(qw);
        return imgList;
    }


}
