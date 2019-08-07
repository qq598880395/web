package com.action;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
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
        List<Img> imglist=imgService.searchImgById(template_id,"yes",null);
        JSONArray jsonArray = new JSONArray();
        for (Img img:imglist) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("img_src",img.getImg_src());
            jsonObject.put("img_href",img.getImg_href());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }

    //查询微官网模块所有图片
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
        DelFile(img_src);
    }

    //更新图片对应的链接
    @ResponseBody
    @RequestMapping(value = "/updataImg_href")
    public void updataImg_href(String img_href,String img_id){
        if (img_href==null||img_href.trim().length()== 0){
            img_href = "#";
        }
        imgService.updataImg_href(img_href,img_id);
    }

    //更新图片状态
    @ResponseBody
    @RequestMapping(value = "/updataImg_status")
    public void updataImg_status(String img_status,String img_id){
        imgService.updataImg_status(img_status,img_id);
    }

    //查询历史所有模板
    @ResponseBody
    @RequestMapping(value = "/searchHistoryHtml")
    public JSONObject SerachHistoryHtml(int page,int limit) {
        JSONObject jsonObject = new JSONObject();
        IPage<Page> pagelist = pageService.searchHistoryHtml(page, limit);

        jsonObject.put("code", 0);
        jsonObject.put("msg", "查询成功");
        jsonObject.put("count", pagelist.getTotal());
        jsonObject.put("data", pagelist.getRecords());
        return jsonObject;
    }

    //删除指定的页面
    @ResponseBody
    @RequestMapping(value = "/delHtml")
    public void delHtml(String page_id,String page_src){
        pageService.delPageById(page_id);
        DelFile(page_src);
    }

    //删除服务器里的文件
    public static void DelFile(String src) {
        File file  = new File(System.getProperty("ROOT")+ src);
        if (file.exists()==true){
            file.delete();
            System.out.println("文件是否存在："+file.exists());//判断文件是否删除成功
        }
    }

    //更新页面状态
    @ResponseBody
    @RequestMapping(value = "/updataPage_status")
    public void updataPage_status(String page_id){
        pageService.updataPage_status(page_id);
    }

    //查询模块中的所有图片
    @ResponseBody
    @RequestMapping(value = "/searchImgs")
    public String searchImgs(int template_id){
        List<Img> img_list = imgService.searchImgsByTemplate_id(template_id);
        JSONArray jsonArray = new JSONArray();
        for (Img img:img_list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("img_src",img.getImg_src());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }
}
