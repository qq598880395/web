package com.action;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.google.gson.Gson;
import com.pojo.Rc_case;
import com.pojo.Recharge;
import com.pojo.Vip;
import com.pojo.Vip_level;
import com.service.VipService;
import com.util.Page;
import com.util.ResultMap;
import com.util.TelMsgLogin;
import com.util.UUIDTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;


@Controller
public class VipAction {
    @Autowired
    private VipService service;

    @RequestMapping(value = "/findallvip")
    //@responsebody用来将返回值解析成json数据 不使用则返回一个跳转路径
    @ResponseBody
    public ResultMap<List<Vip>> backContent(Page page, @RequestParam("limit") int limit){
        page.setRows(limit);

        List<Vip> vipList=service.selectPageList(page);
        int totals=service.selectPageCount(page);
        System.out.println();
        System.out.println(totals);
        page.setTotalRecord(totals);
        return new ResultMap<List<Vip>>(0,"",totals,vipList);
    }

    @RequestMapping(value = "/findalllist")
    //@responsebody用来将返回值解析成json数据 不使用则返回一个跳转路径
    @ResponseBody
    public ResultMap<List<Recharge>> backlist(Page page, @RequestParam("limit") int limit){
        page.setRows(limit);

        List<Recharge> rcList=service.selectPageList1(page);
        int totals=service.selectPageCount1(page);
        System.out.println();
        System.out.println(totals);
        page.setTotalRecord(totals);
        return new ResultMap<List<Recharge>>(0,"",totals,rcList);
    }


    @RequestMapping("/add")
    public  String addvip(String vip_tel){
        String vip_id = UUIDTool.getUUID();
        service.addVip(vip_id,vip_tel);
        return "background.jsp";
    }

    @RequestMapping("/addopenid")
    public String addvipbyopenid(){
        int openid = 111;
        String vip_id = UUIDTool.getUUID();
        service.addVipbyopenid(openid,vip_id);
        return "background.jsp";
    }

    @RequestMapping("/updateMsg")
    @ResponseBody
    public int updateMsg(String vip_id,String vip_name,String vip_IDcard,String vip_tel) throws UnsupportedEncodingException {
        System.out.println();
        System.out.println();
        System.out.println();
//        vip_name= URLDecoder.decode(vip_name,"utf-8");
        System.out.println(vip_name);
        System.out.println();
        System.out.println();

        int x = service.updateMsg(vip_id,vip_name,vip_IDcard,vip_tel);
        return x;
    }

    @ResponseBody
    @RequestMapping(value="/delete")
    public  int  delete( String vip_id ){
        int num=0;
        if (vip_id!= null) {
            num = service.deleteMsg(vip_id);
        }
        System.out.println(vip_id);
        return num;

    }
    @ResponseBody
    @RequestMapping("/sendCode")
    public  String sendCode (String vip_tel) throws  ClientException{
        String code = TelMsgLogin.Setcode(vip_tel);
        int count = service.countBytel(vip_tel,1001);
        int status;
        JSONObject json =new JSONObject();
        if(count==0)
        {
            status=0;
            json.put("code",code);
            json.put("status",status);
        }
        else if(count==1)
        {
            status=1;
            json.put("code",code);
            json.put("status",status);
        }
        String json1=json.toString();
        System.out.println(json1);
        return  json1;
    }
    @ResponseBody
    @RequestMapping(value="/login")
    public String login(String vip_tel,  String status) {
        System.out.println(vip_tel);
        System.out.println(status);
        JSONObject json =new JSONObject();
        if(status.equals("0"))
        {
            String vip_id = UUIDTool.getUUID();
            System.out.println(vip_id);
            service.addVip(vip_id,vip_tel);
        }

        Vip vip = service.findByVip_tel(vip_tel,1001);
        String vip_id = vip.getVip_id();
        json.put("vip_id",vip_id);
        json.put("vip_name",vip.getVip_name());
        json.put("vip_money",vip.getVip_money());
        json.put("vip_tel",vip.getVip_tel());
        json.put("vip_IDcard",vip.getVip_IDcard());
        json.put("hotel_id",vip.getHotel_id());
        String json1=json.toString();
        System.out.println(json1);



        return json1;
    }
//    @ResponseBody
    @RequestMapping(value="/dosession")
    public void dosession(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //编码规范
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        String vip_tel = request.getParameter("vip_tel");
        System.out.println();
        System.out.println();
        System.out.println(vip_tel);
        Vip vip = service.findByVip_tel(vip_tel,1001);
//        vip.setVip_money(2.2);
        PrintWriter out = response.getWriter();//测试用 printWriter可用来创建一个文件并向文本文件写入数据。可以理解为java中的文件输出
        //转GSON
        Gson gson=new Gson();
        String vipGson= gson.toJson(vip);
        //将vip存进session
        HttpSession session =request.getSession();
        session.setAttribute("vip",vipGson);


    }

    @RequestMapping("/checkVip")
    public void checkVip(HttpServletRequest request,HttpServletResponse response) {
        //编码规范
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        //获取session值
        HttpSession session = request.getSession();
        Object vip = session.getAttribute("vip");
        try {
            PrintWriter out = response.getWriter();
            out.write((String) vip);
        } catch (Exception e) {
            System.out.println("Nothing session");
        }
    }


    @ResponseBody
    @RequestMapping("/rcCase")
    public int rcCase(int rc_a,int rc_b,int rc_c,int rc_a_regiv,int rc_b_regiv,int rc_c_regiv){
        int rc_caseid=1;
        int x = service.rcCase(rc_a,rc_b,rc_c,rc_a_regiv,rc_b_regiv,rc_c_regiv,rc_caseid);
        System.out.println(rc_a+","+rc_a_regiv+","+rc_b+","+rc_b_regiv+","+rc_c+","+rc_c_regiv);
        return x;
    }
    @ResponseBody
    @RequestMapping("/findMsg")
    public String findMsg(String vip_id){
        System.out.println();
        System.out.println();
        System.out.println(vip_id);
        Vip vip = service.findMsg(vip_id);
        String json= JSONObject.toJSONString(vip);
//        System.out.println(json);

        return json;
    }
    @ResponseBody
    @RequestMapping("/findlevelMsg")
    public String findlevelMsg(int level_id){
        Vip_level level = service.findlevelMsg(level_id);
        String json= JSONObject.toJSONString(level);
//        System.out.println(json);

        return json;
    }
    @ResponseBody
    @RequestMapping("/getRc")
    public String getRc(int rc_caseid){
        Rc_case rc_case = service.getRc(rc_caseid);
        String json = JSONObject.toJSONString(rc_case);
//        System.out.println(json);
        return json;
    }

    @ResponseBody
    @RequestMapping("/recharge")
    public int recharge(String vip_id , double rc_cost ,double level_num){
        System.out.println(rc_cost);
        Rc_case rc_case = service.getRc(1);
        int level_id=1 ;
        double sum = rc_cost+level_num;
        if(sum>=200){
            level_id=2;
        }
        if(sum>=800){
            level_id=3;
        }
        if(sum>=2000){
            level_id=4;
        }
        if(sum>=5000){
            level_id=5;
        }
        if(sum>=12000){
            level_id=6;
        }
        if(sum>=50000){
            level_id=7;
        }

        double rc_num1=0;//最终结果
        int rc_a=0,rc_b=0,rc_c=0,rc_a_regiv,rc_b_regiv,rc_c_regiv ,num,n,m;//num 存优惠方案  m存翻倍值 n中间数
        rc_a=rc_case.getRc_a();
        rc_b=rc_case.getRc_b();
        rc_c=rc_case.getRc_c();
        rc_a_regiv=rc_case.getRc_a_regiv();
        rc_b_regiv=rc_case.getRc_b_regiv();
        rc_c_regiv=rc_case.getRc_c_regiv();

        if(rc_a==0&&rc_b==0&&rc_c==0)
        {
            rc_num1=rc_cost;
        }
        else if(rc_a!=0&&rc_b==0&&rc_c==0)
        {

            if(rc_cost>=rc_a){
                 n=(int)(rc_cost-rc_cost%rc_a);
                 m=n/rc_a;
                num=rc_a_regiv;
                rc_num1= rc_cost+num*m;
            }
            else {
                num = 0;
                rc_num1=rc_cost;
            }
        }
        else if(rc_a==0&&rc_b!=0&&rc_c==0)
        {

            if(rc_cost>=rc_b){
                 n=(int)(rc_cost-rc_cost%rc_b);
                 m=n/rc_b;
                num=rc_b_regiv;
                rc_num1= rc_cost+num*m;
            }
            else {
                num = 0;
                rc_num1=rc_cost;
            }
        }
        else if(rc_a==0&&rc_b==0&&rc_c!=0)
        {

            if(rc_cost>=rc_c){
                 n=(int)(rc_cost-rc_cost%rc_c);
                 m=n/rc_c;
                num=rc_c_regiv;
                rc_num1= rc_cost+num*m;
            }
            else {
                num = 0;
                rc_num1=rc_cost;
            }
        }
        else if (rc_a!=0&&rc_b!=0&&rc_c==0)
        {
            if (rc_cost<=rc_a)
            {
                num=0;
                rc_num1=rc_cost;
            }
            else if(rc_cost>=rc_a&&rc_cost<rc_b)
            {
                num=rc_a_regiv;
                rc_num1 = rc_cost+num;
            }
            else if(rc_cost>=rc_b)
            {
                 n=(int)(rc_cost-rc_cost%rc_b);
                 m=n/rc_b;
                num=rc_b_regiv;
                rc_num1= rc_cost+num*m;
            }
        }
        else if (rc_a!=0&&rc_b==0&&rc_c!=0)
        {
            if (rc_cost<=rc_a)
            {
                num=0;
                rc_num1=rc_cost;
            }
            else if(rc_cost>=rc_a&&rc_cost<rc_c)
            {
                num=rc_a_regiv;
                rc_num1 = rc_cost+num;
            }
            else if(rc_cost>=rc_c)
            {
                n=(int)(rc_cost-rc_cost%rc_c);
                m=n/rc_c;
                num=rc_c_regiv;
                rc_num1= rc_cost+num*m;
            }
        }
        else if (rc_a==0&&rc_b!=0&&rc_c!=0)
        {
            if (rc_cost<=rc_b)
            {
                num=0;
                rc_num1=rc_cost;
            }
            else if(rc_cost>=rc_b&&rc_cost<rc_c)
            {
                num=rc_b_regiv;
                rc_num1 = rc_cost+num;
            }
            else if(rc_cost>=rc_c)
            {
                n=(int)(rc_cost-rc_cost%rc_c);
                m=n/rc_c;
                num=rc_c_regiv;
                rc_num1= rc_cost+num*m;
            }
        }
        else if(rc_a!=0&&rc_b!=0&&rc_c!=0)
        {
            if (rc_cost<rc_a)
            {
                num=0;
                rc_num1=rc_cost;
            }
            else if(rc_cost>=rc_a&&rc_cost<rc_b)
            {
                num=rc_a_regiv;
                rc_num1=rc_cost+num;
            }
            else if(rc_cost>=rc_b&&rc_cost<rc_c)
            {
                num=rc_b_regiv;
                rc_num1 = rc_cost+num;

            }
            else if(rc_cost>=rc_c)
            {
                n=(int)(rc_cost-rc_cost%rc_c);
                m=n/rc_c;
                num=rc_c_regiv;
                rc_num1= rc_cost+num*m;
            }
        }
        String rc_id = UUIDTool.getUUID();
        System.out.println(rc_num1);
        int x = service.recharge(vip_id,rc_num1,rc_id,rc_cost,level_id,sum);
        return x;

    }
    @ResponseBody
    @RequestMapping("/findbytel")
    public int findbytel(String vip_tel){
        int x = service.countBytel(vip_tel,1001);
        return x;

    }

}
