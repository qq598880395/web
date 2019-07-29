package com.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dao.ImgDAO;
import com.pojo.Img;
import com.pojo.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImgService {

    @Autowired
    private ImgDAO imgDAO;

    //添加图片
    public int addImage(String newFilePath,Integer tmpid){
        Img img=new Img();
        img.setImg_src(newFilePath);
        img.setTemplate_id(tmpid);
        int n=imgDAO.insert(img);
        return n;
    }

    //查询图片
    public List<Img> searchImgById(String tmpid,String imgStatus){
        QueryWrapper qw =new QueryWrapper();
        qw.eq("template_id",tmpid);
        qw.eq("img_status",imgStatus);
        List<Img> imgList=imgDAO.selectList(qw);
        return imgList;
    }


}
