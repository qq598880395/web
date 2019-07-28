package com.action;

import com.alibaba.fastjson.JSONObject;
import org.omg.CORBA.StringHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.net.URLDecoder;


@Controller
public class CreateHtmlAction {
    private String pageurl = System.getProperty("ROOT") + "/page"; // 上传文件的目录
    @ResponseBody
    @RequestMapping(value="/createHTML")
    public String createHTML(String htmltext) throws IOException {
        htmltext = "<!DOCTYPE html>\n" +"<html>"+htmltext+"</html>";
        System.out.println(htmltext);

        File savePathFile =new File(pageurl);
        if (savePathFile.exists()==false){
            savePathFile.mkdirs();
        }
        String pageName = System.currentTimeMillis()+"";
        File file = new File(pageurl+"/"+pageName+".html");
        // 创建一个新文件
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        osw.write(htmltext);
        // 关闭输出流
        osw.close();
        fos.close();
        System.out.println("输入完成");
        boolean fvar = file.createNewFile();
        if (fvar) {
            System.out.println("File has been created successfully!");
        } else {
            System.out.println("File already present at the specified location!");
        }


        JSONObject json =new JSONObject();
        json.put("code",200);
        json.put("msg","success");
        json.put("url","/page/"+pageName+".html");
        return json.toString();
    }


}
