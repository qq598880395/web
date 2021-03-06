package com.action;

import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.pojo.Img;
import com.service.ArticleService;
import com.service.ImgService;
import com.service.PageService;
import com.vo.ImgHrefVo;
import com.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping("/upload")
@Controller
public class UpLoadAction {
    @Autowired
    private PageService pageService;
    @Autowired
    private ImgService imgService;
    @Autowired
    private ArticleService articleService;

    private String pageurl = System.getProperty("ROOT") + "/page"; // 上传页面的目录
    private String uploadPath = System.getProperty("ROOT") + "/img/lunbo"; // 上传轮播图片的目录
    private String uploadMod = System.getProperty("ROOT") + "/img/modnav"; // 上传mod图片的目录
    private String ArticleImgPath = System.getProperty("ROOT") + "/img/article"; // 上传图片的目录



    @RequestMapping("/uploadImage")
    @ResponseBody
    public JSONObject uploadImage( @RequestParam("file")MultipartFile file, String params) {
        String href = "#";
        List<ImgHrefVo> testDemos = JSON.parseArray(params, ImgHrefVo.class);
        for (int i = 0; i < testDemos.size(); i++) {
            if (testDemos.get(i).getFilename().equals(file.getOriginalFilename())) {
                //判断herf参数是否为空
                if (testDemos.get(i).getImg_href() != null && testDemos.get(i).getImg_href().length() > 0) {
                    href = testDemos.get(i).getImg_href();
                }
                break;
            }
        }
        JSONObject res = new JSONObject();
        JSONObject resUrl = new JSONObject();
        if (!file.isEmpty()) {
            try {
                //得到旧文件名
                String oldFileName = file.getOriginalFilename();
                //得到后缀名
                int index = oldFileName.lastIndexOf(".");
                String extName = oldFileName.substring(index);
                //新文件名
                String newFileName = System.nanoTime() + extName;
                File savePathFile = new File(uploadPath);
                if (savePathFile.exists() == false) {
                    savePathFile.mkdirs();
                }
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(uploadPath, newFileName)));
                out.write(file.getBytes());
                imgService.addImage(newFileName,"img/lunbo/"+newFileName,1,href);

                out.flush();
                out.close();
            } catch (IOException e) {
                res.put("code", 1);
                res.put("msg", "上传出错");
                res.put("data", resUrl);
                return res;
            }
            res.put("code", 0);
            res.put("msg", "上传成功");
            res.put("data", resUrl);
            return res;
        } else {
            res.put("code", 0);
            res.put("msg", "上传为空");
            res.put("data", resUrl);
            return res;
        }

    }
    //上传mod图片
    @RequestMapping("/uploadModImg")
    @ResponseBody
    public JSONObject uploadModImg(@RequestParam("file") MultipartFile file) {
        JSONObject res = new JSONObject();
        String modUrl = null;
        if (!file.isEmpty()) {
            try {
                //得到旧文件名
                String oldFileName = file.getOriginalFilename();
                //得到后缀名
                int index = oldFileName.lastIndexOf(".");
                String extName = oldFileName.substring(index);
                //新文件名
                String newFileName = System.nanoTime() + extName;
                File savePathFile = new File(uploadMod);
                if (savePathFile.exists() == false){
                    savePathFile.mkdirs();
                }
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(uploadMod, newFileName)));
                out.write(file.getBytes());
                out.flush();
                out.close();
                imgService.addImage(newFileName,"img/modnav/"+newFileName,3,"#");
                modUrl = "/img/modnav/"+newFileName;
            } catch (IOException e) {
                res.put("code", 1);
                res.put("msg", "上传出错");
                res.put("modUrl", modUrl);
                return res;
            }
            res.put("code", 0);
            res.put("msg", "上传成功");
            res.put("modUrl", modUrl);
            return res;
        } else {
            res.put("code", 0);
            res.put("msg", "上传为空");
            res.put("modUrl", modUrl);
            return res;
        }
    }

    //上传页面
    @ResponseBody
    @RequestMapping(value = "/createHTML")
    public String createHTML(String htmltext, String page_name, String itexist) throws IOException {
        htmltext = "<!DOCTYPE html>\n" + "<html id='html'>" + htmltext + "</html>";
        String pageName = System.currentTimeMillis() + "";
        File file = new File(pageurl + "/" + pageName + ".html");
        // 创建一个新文件
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        osw.write(htmltext);
        // 关闭输出流
        osw.close();
        System.out.println("输入完成");
        //获取当前系统时间
        Date date = new Date();
        Timestamp now = new Timestamp(date.getTime());
        //页面信息存入数据库
        PageVO vo = new PageVO();
        vo.setPage_name(page_name);
        vo.setPage_src("/page/" + pageName + ".html");
        vo.setPage_status("no");
        vo.setCreate_time(now.toString());
        if (itexist.equals("false")) {
            //新增
            pageService.addPage(vo);

        } else {
            //查询原文件路径并删除
            String src = pageService.selectPage_src(page_name);
            System.out.println("滚出来" + src);
            HtmlAction.DelFile(src);
            //覆盖原有的
            pageService.coverPage(vo, page_name);
        }
        pageService.updataPage(page_name);
        com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
        json.put("code", 0);
        json.put("msg", "success");
        json.put("url", "/page/" + pageName + ".html");
        return json.toString();

    }

    //上传文章模板图片
    @RequestMapping(value = "/uploadArticle")
    public String uploadArticle(@RequestParam("title")String title ,@RequestParam("textarea")String textarea, String imgsrc,String file) {
        System.out.println(title);
        System.out.println(textarea);
        System.out.println(imgsrc);
        Date date =new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject res = new JSONObject();
        JSONObject resUrl = new JSONObject();
        String changeImgSrc =imgsrc.replace('"',' ').trim();
        int n = imgService.addImage(changeImgSrc, "img/article/" +changeImgSrc, 2, null);

        List<Img> artimg_list = imgService.searchImgById(2, "yes", "img/article/" + changeImgSrc);
        if (artimg_list != null && artimg_list.size() > 0) {
            Integer id = artimg_list.get(0).getImg_id();
            int n1 = articleService.addArticle(title, textarea, id,format.format(date).toString());
        }
        res.put("code",0);
        res.put("msg","表单上传成功");
        res.put("data",resUrl);
        return res.toString();
    }

    //得到文件存储目录
    @ResponseBody
    @RequestMapping(value = "/getImgUrl")
    public JSONObject getImgUrl(@RequestParam("file") MultipartFile file){
        JSONObject res = new JSONObject();
        JSONObject resUrl = new JSONObject();
        try {
            //得到旧文件名
            String oldFileName = file.getOriginalFilename();
            //得到后缀名
            int index = oldFileName.lastIndexOf(".");
            String extName = oldFileName.substring(index);
            //新文件名
            String newFileName = System.nanoTime() + extName;
            resUrl.put("img_src",newFileName);
            File savePathFile = new File(ArticleImgPath);
            if (savePathFile.exists() == false) {
                savePathFile.mkdirs();
            }
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(new File(ArticleImgPath, newFileName)));
            out.write(file.getBytes());
            out.flush();
            out.close();
        }catch (IOException e){
            res.put("code", 1);
            res.put("msg", "上传出错");
            res.put("data", resUrl);
            return res;
        }
        res.put("code", 0);
        res.put("msg", "上传成功");
        res.put("data", resUrl);
        return res;
    }


    //根据字段判断是否存在
    @ResponseBody
    @RequestMapping(value = "/itExist")
    public String itExist(String value, String column) {
        String result = pageService.pageitExist(value, column);
        return result;
    }


}
