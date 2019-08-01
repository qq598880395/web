package com.action;

import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.generator.config.IFileCreate;
import com.pojo.Img;
import com.pojo.Page;
import com.service.ImgService;
import com.service.PageService;
import com.sun.org.apache.xml.internal.security.utils.XalanXPathAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.json.JsonArray;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/html")
public class HtmlAction {
    @Autowired
    private PageService pageService;

    @Autowired
    private ImgService imgService;

    //查询当前微官网页面模板
    @ResponseBody
    @RequestMapping(value = "/searchNowHTML")
    public String SearchNowHtml(){
        Page page  =pageService.searchNowPage();
        String page_src = page.getPage_src();
        System.out.println(page_src);
        JSONObject json = new JSONObject();
        json.put("page_src",page_src);
        return json.toString();
    }

    //查询当前微官网模块图片
    @ResponseBody
    @RequestMapping(value = "/searchNowImg")
    public List<Img> SerachNowImg(Integer tmpid){
        List<Img> imglist=imgService.searchImgById(tmpid,"yes");
        return imglist;
    }

    //查询当前微官网模块图片
    @ResponseBody
    @RequestMapping(value = "/searchImg")
    public JSONObject SerachImg(){
        JSONObject jsonObject = new JSONObject();
        List<Img> imglist=imgService.searchImgById(1);
            jsonObject.put("code",0);
            jsonObject.put("msg","");
            jsonObject.put("count",imglist.size());
            jsonObject.put("data",imglist);
        return jsonObject;
    }

    //删除指定的图片
    @RequestMapping(value = "/delImg")
    public void delImg(String img_id,String img_src){
        imgService.delImgById(img_id);
        System.out.println(img_src);
        File file  = new File(System.getProperty("ROOT")+img_src);
        System.out.println(System.getProperty("ROOT")+img_src);
        if (file.exists()==true){
            file.delete();
            System.out.println("文件是否存在："+file.exists());//判断文件是否删除成功
        }
    }

    //更新图片对应的链接
    @RequestMapping(value = "/updataImg_href")
    public void updataImg_href(String img_href,String img_id){
        imgService.updataImg_href(img_href,img_id);
        System.out.println(img_href);
        System.out.println(img_id);
    }




}
