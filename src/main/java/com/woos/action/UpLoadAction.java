package com.woos.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

@Controller
public class UpLoadAction {

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(MultipartRequest mr) throws IOException {
        //从请求中取得文件名
        Iterator<String> it =mr.getFileNames();
        if(it.hasNext()==false){
            return "";
        }
        String name =it.next();
        MultipartFile image=mr.getFile(name);

        //得到旧文件名
        String oldFileName =image.getOriginalFilename();
        //得到后缀名
        int index =oldFileName.lastIndexOf(".");
        String extName =oldFileName.substring(index);
        //新文件名
        String newFileName=System.currentTimeMillis()+extName;

        //存放的路径
        String savePath= System.getProperty("ROOT")+"/tmp";
        File savePathFile =new File(savePath);
        if (savePathFile.exists()==true){
            savePathFile.mkdirs();
        }
        //传输
        File savePathTmp =new File(savePathFile,newFileName);
        image.transferTo(savePathTmp);
        String result ="/tmp"+newFileName;

        return result;
    }
}
