package com.action;

import cn.hutool.json.JSONObject;
import com.mysql.jdbc.CharsetMapping;
import com.service.ImgService;
import com.service.PageService;
import com.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
@RequestMapping("/upload")
@Controller
public class UpLoadAction {
    @Autowired
    private PageService pageService;
    @Autowired
    private ImgService imgService;

    private String pageurl = System.getProperty("ROOT") + "/page"; // 上传页面的目录
    private String uploadPath = System.getProperty("ROOT") + "/img/lunbo"; // 上传图片的目录

    @RequestMapping("/uploadImage")
    @ResponseBody
    public JSONObject uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String name = request.getParameter("name");
        String href = request.getParameter("href");
        System.out.println(name);
        System.out.println(href);
        JSONObject res = new JSONObject();
        JSONObject resUrl = new JSONObject();
        if (!file.isEmpty()) {
            Map<String, String> resObj = new HashMap<>(CharsetMapping.MAP_SIZE);
            try {
                //得到旧文件名
                String oldFileName =file.getOriginalFilename();
                //得到后缀名
                int index =oldFileName.lastIndexOf(".");
                String extName =oldFileName.substring(index);
                //新文件名
                String newFileName=System.nanoTime()+extName;
                File savePathFile =new File(uploadPath);
                if (savePathFile.exists()==false){
                    savePathFile.mkdirs();
                }
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(uploadPath, newFileName)));
                out.write(file.getBytes());
                out.flush();
                out.close();
               // imgService.addImage("img/"+newFileName,null);
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

    //上传页面
        @ResponseBody
        @RequestMapping(value="/createHTML")
        public String createHTML(String htmltext) throws IOException {
            htmltext = "<!DOCTYPE html>\n" +"<html>"+htmltext+"</html>";
            System.out.println(htmltext);

            String pageName = System.currentTimeMillis()+"";
            File file = new File(pageurl+"/"+pageName+".html");
            // 创建一个新文件
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            osw.write(htmltext);
            // 关闭输出流
            osw.close();
            System.out.println("输入完成");

            //页面信息存入数据库
            PageVO vo = new PageVO();
            vo.setPage_name(pageName);
            vo.setPage_src("/page/"+pageName+".html");
            vo.setPage_status("no");
            pageService.addPage(vo);
            pageService.updataPage(pageName);

            com.alibaba.fastjson.JSONObject json =new com.alibaba.fastjson.JSONObject();
            json.put("code",200);
            json.put("msg","success");
            json.put("url","/page/"+pageName+".html");
            return json.toString();
        }



}





//    public String upload(MultipartRequest mr) throws IOException {
//        //从请求中取得文件名
//        Iterator<String> it =mr.getFileNames();
//        if(it.hasNext()==false){
//            return "";
//        }
//        String name =it.next();
//        MultipartFile image=mr.getFile(name);
//
//        //得到旧文件名
//        String oldFileName =image.getOriginalFilename();
//        //得到后缀名
//        int index =oldFileName.lastIndexOf(".");
//        String extName =oldFileName.substring(index);
//        //新文件名
//        String newFileName=System.currentTimeMillis()+extName;
//
//        //存放的路径
//        String savePath= System.getProperty("ROOT")+"/tmp";
//        File savePathFile =new File(savePath);
//        if (savePathFile.exists()==true){
//            savePathFile.mkdirs();
//        }
//        //传输
//        File savePathTmp =new File(savePathFile,newFileName);
//        image.transferTo(savePathTmp);
//        String result ="/tmp"+newFileName;
//
//        return result;
//    }
//}
