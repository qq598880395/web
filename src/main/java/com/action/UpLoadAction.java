package com.action;

import cn.hutool.json.JSONObject;
import com.mysql.jdbc.CharsetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UpLoadAction {

    File tempPathFile;

    private String uploadPath = System.getProperty("ROOT") + "/tmp"; // 上传文件的目录

    @RequestMapping("/upload")
    @ResponseBody
    public JSONObject uploadImage(@RequestParam("file") MultipartFile file) {
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
