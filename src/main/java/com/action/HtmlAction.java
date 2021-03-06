package com.action;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dao.ImgDAO;
import com.pojo.Article;
import com.pojo.Img;
import com.pojo.Page;
import com.service.ArticleService;
import com.service.ImgService;
import com.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.json.JsonObject;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/html")
public class HtmlAction {
    @Autowired
    private PageService pageService;

    @Autowired
    private ImgService imgService;

    @Autowired
    private ArticleService articleService;
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

    //批量删除页面
    @ResponseBody
    @RequestMapping("/delMostPage")
    public  int delMostPage(String params){
        List testDemos = JSON.parseArray(params);
        com.alibaba.fastjson.JSONArray pageJson = new com.alibaba.fastjson.JSONArray(testDemos);
        int n = pageService.delMostPage(pageJson);
        DelMostFile(pageJson,"page_src");
        return n;
    }

    //批量删除图片
    @ResponseBody
    @RequestMapping("/delMostImg")
    public  int delMostImg(String params){
        List testDemos = JSON.parseArray(params);
        com.alibaba.fastjson.JSONArray imgJson = new com.alibaba.fastjson.JSONArray(testDemos);
        int n = imgService.delMostImg(imgJson);
        DelMostFile(imgJson,"img_src");
         return n;
    }

    //批量删除服务器里的文件
    private void DelMostFile(com.alibaba.fastjson.JSONArray imgJson,String src) {
        com.alibaba.fastjson.JSONArray jsonArray = null;
        jsonArray = new com.alibaba.fastjson.JSONArray(imgJson);
        for (int i=0;i<jsonArray.size();i++){
            File file = new File(System.getProperty("ROOT") + jsonArray.getJSONObject(i).get(src));
            if (file.exists()==true){
                file.delete();
//                System.out.println("文件还在嘛：" + file.exists());//判断文件是否删除成功
            }
        }
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

    //查询文章模块所有文章
    @ResponseBody
    @RequestMapping(value = "/searchArticle")
    public JSONObject searchArticle(int page ,int limit){
        JSONObject jsonArray =new JSONObject();
        JSONArray jsonArray2=new JSONArray();
        int sum =articleService.getArticleCount();

            List<Article> article_list =articleService.searchArticle(page,limit);
            if (article_list!=null&& article_list.size()>0){
                for (Article x: article_list) {
                    JSONObject jsonItem =new JSONObject();
                    jsonItem.put("article_id",x.getArticle_id());
                    jsonItem.put("article_title",x.getArticle_title());
                    jsonItem.put("article_text",x.getArticle_text());
                    jsonItem.put("article_time",x.getArticle_time());
                    jsonItem.put("img_id",x.getImg_id());
                    String img_src =imgService.searchArtImgById(x.getImg_id()).getImg_src();
                    jsonItem.put("img_src", img_src);
                    jsonArray2.add(jsonItem);
                }
            }
            jsonArray.put("code",0);
            jsonArray.put("msg","");
            jsonArray.put("count",sum);
            jsonArray.put("data",jsonArray2);
            return jsonArray;


    }

    //删除指定文章
    @ResponseBody
    @RequestMapping(value = "/delArticle")
    public int delArticle(Integer article_id,Integer img_id){
        int n =articleService.delArticle(article_id,img_id);
        return n;
    }

    //一键删除指定文章
    @ResponseBody
    @RequestMapping(value = "/delMostArticle")
    public  int delMostArticle(String params){
        List testDemos = JSON.parseArray(params);
        com.alibaba.fastjson.JSONArray articleJson = new com.alibaba.fastjson.JSONArray(testDemos);
        int n = articleService.delMostArticle(articleJson);
        DelMostFile1(articleJson);
        return n;
    }
//根据图片id删除工程里的图片
    private void DelMostFile1(com.alibaba.fastjson.JSONArray articleJson) {
        com.alibaba.fastjson.JSONArray jsonArray = null;
        jsonArray = new com.alibaba.fastjson.JSONArray(articleJson);
        for (int i=0;i<jsonArray.size();i++){
            int img_id = (int) jsonArray.getJSONObject(i).get("img_id");
            String src = articleService.selectImgById(img_id);
            File file = new File(System.getProperty("ROOT") + src);
            if (file.exists()==true){
                file.delete();
//                System.out.println("文件还在嘛：" + file.exists());//判断文件是否删除成功
            }
        }
    }

}
