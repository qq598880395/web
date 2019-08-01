package com.action;

import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSONArray;
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



}
