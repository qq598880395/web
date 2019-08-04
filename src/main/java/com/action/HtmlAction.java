package com.action;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pojo.Img;
import com.pojo.Page;
import com.service.ImgService;
import com.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
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
    public String SerachNowImg(Integer template_id){
        List<Img> imglist=imgService.searchImgById(template_id,"yes");
        JSONArray jsonArray = new JSONArray();
        for (Img img:imglist) {
            jsonArray.add(img.getImg_src());
        }
        System.out.println();

        return jsonArray.toString();
    }

    //查询当前微官网模块图片
    @ResponseBody
    @RequestMapping(value = "/searchImg")
    public JSONObject SerachImg(int page,int limit){
        JSONObject jsonObject = new JSONObject();
        IPage<Img> imglist = imgService.searchImgById(1,page,limit);

            jsonObject.put("code",0);
            jsonObject.put("msg","");
            jsonObject.put("count",imglist.getTotal());
            jsonObject.put("data",imglist.getRecords());
        return jsonObject;
    }

    //删除指定的图片
    @ResponseBody
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
    @ResponseBody
    @RequestMapping(value = "/updataImg_href")
    public void updataImg_href(String img_href,String img_id){
        imgService.updataImg_href(img_href,img_id);
        System.out.println(img_id);
    }

    //更新图片状态
    @ResponseBody
    @RequestMapping(value = "/updataImg_status")
    public void updataImg_status(String img_status,String img_id){
        imgService.updataImg_status(img_status,img_id);
        System.out.println(img_id);
    }



}
