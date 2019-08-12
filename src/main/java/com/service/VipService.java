package com.service;

import com.alibaba.fastjson.JSONArray;
import com.dao.Rc_caseDAO;
import com.dao.RechargeDAO;
import com.dao.VipDAO;
import com.dao.Vip_levelDAO;
import com.pojo.Rc_case;
import com.pojo.Recharge;
import com.pojo.Vip;
import com.pojo.Vip_level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VipService {
    @Autowired
    private VipDAO vipDAO ;
    @Autowired
    private Rc_caseDAO rc_caseDAO;
    @Autowired
    private RechargeDAO rechargeDAO;
    @Autowired
    private Vip_levelDAO vip_levelDAO;

    public String getVip(){
        List list = vipDAO.findAll();
        String jsonString= JSONArray.toJSONString(list);
        System.out.println(jsonString);
        return jsonString;
    }
    public int count(){
        int x = vipDAO.count();
        return  x;
    }
    public int addVip(String id,String tel){
        vipDAO.addVipBytel(id,tel);
        int x = 1;
        return x;
    }
    public int addVipbyopenid(int openid,String vip_id){
        vipDAO.addVipByopenid(openid,vip_id);
        int x = 1;
        return x;
    }
    public int updateMsg(String vip_id ,String vip_name,String vip_IDcrad, String vip_tel){

        int x = vipDAO.updateVipmsg(vip_id,vip_name,vip_IDcrad,vip_tel);
        return x;
    }
    public int deleteMsg(String vip_id){
        int x = vipDAO.deleteVip(vip_id);
        return x;
    }
    public int countByid(String vip_id){
        int x = vipDAO.countByVip_id(vip_id);
        return  x;
    }
    public int countBytel(String vip_tel,int hotel_id){
        int x = vipDAO.countByVip_tel(vip_tel,1001);
        return  x;
    }
    public int rcCase(int rc_a,int rc_b,int rc_c,int rc_a_regiv,int rc_b_regiv,int rc_c_regiv,int rc_caseid){
        int x = rc_caseDAO.updateRcCase(rc_a,rc_b,rc_c,rc_a_regiv,rc_b_regiv,rc_c_regiv,rc_caseid);
        return x;
    }
    public Vip findMsg(String vip_id){
        Vip vip = vipDAO.findbyVip_id(vip_id);
        return vip;
    }
    public Vip_level findlevelMsg(int level_id){
        Vip_level vip_level = vip_levelDAO.findbylevel_id(level_id);
        return vip_level;
    }
    public Rc_case getRc(int rc_caseid) {
        Rc_case rc_case = rc_caseDAO.findbyRc_id(rc_caseid);
//        String jsonString = JSONObject.toJSONString(rc_case);
//        System.out.println(jsonString);
        return rc_case;
    }

    public int recharge(String vip_id,double rc_cost,String rc_id,double rc_cost_1st,int level_id,double level_num ){
        Vip vip =vipDAO.findbyVip_id(vip_id);
        double vip_money = vip.getVip_money();
        String vip_name = vip.getVip_name();
        vip_money=vip_money+rc_cost;
        int hotel_id=vip.getHotel_id();
        String vip_tel=vip.getVip_tel();
        vipDAO.updateVip_money(vip_id,vip_money,level_id,level_num);
        int x=rechargeDAO.addOrder(vip_id,rc_id,rc_cost,hotel_id,vip_name,vip_tel,rc_cost_1st);
        return x;
    }
    public Vip findByVip_tel(String vip_tel, int hotel_id){
        Vip vip = vipDAO.findbyVip_tel(vip_tel,hotel_id);
        return vip;
    }

    public List<Vip> selectPageList(com.util.Page page) {
        List<Vip> list = vipDAO.selectPageList(page);
        return list;
    }

    public Integer selectPageCount(com.util.Page page) {

        return vipDAO.selectPageCount(page);
    }
    public List<Recharge> selectPageList1(com.util.Page page) {
        List<Recharge> list = rechargeDAO.selectPageList(page);
        return list;
    }

    public Integer selectPageCount1(com.util.Page page) {

        return rechargeDAO.selectPageCount(page);
    }

}

