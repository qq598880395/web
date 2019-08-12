package com.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dao.ArticleDAO;
import com.dao.ImgDAO;
import com.pojo.Article;
import com.pojo.Img;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleDAO articleDAO;

    @Autowired
    private ImgDAO imgDAO;

    /**
     *
     * @param article_title
     * @param article_text
     * @param img_id 与img表img_id外键绑定
     * @return
     */
    public int addArticle(String article_title,String article_text,Integer img_id,String article_time){
        Article article =new Article();
        article.setArticle_title(article_title);
        article.setArticle_text(article_text);
        article.setImg_id(img_id);
        article.setArticle_time(article_time);
        int n=articleDAO.insert(article);
       return n;
    }

    /**
     * 分页查询文章
     * @param page
     * @param limit
     * @return
     */
    public List<Article> searchArticle(int page, int limit){
        QueryWrapper qw =new QueryWrapper();
        int count =articleDAO.getArticleCount();
//        int nowPage;
//        if (page!=0&&limit!=0){
//             nowPage =(count%limit==0)? count/limit:count/limit+1 ;
//        }

        //越界判断
//        if (page<=0){
//            page =0;
//        }if (page>nowPage){
//            page =nowPage;
//        }
        if(page==0&&limit==0){
            List<Article> articleList2 =articleDAO.searchAllArticle();
            return articleList2;
        }
        List<Article> articleList =articleDAO.getAllArticle((page-1)*limit,page*limit);
        return articleList;
    }

    //普通查询文章
    public List<Article> searchAllArticle(){
        List Article_list =articleDAO.searchAllArticle();
        return Article_list;
    }
    //得到文章的总数
    public int getArticleCount(){
        int sum =articleDAO.getArticleCount();
        return sum;
    }
    //删除指定文章以及对应图片
    public int delArticle(Integer article_id,Integer img_id){
        QueryWrapper qw =new QueryWrapper();
        QueryWrapper imgQw=new QueryWrapper();
        qw.eq("article_id",article_id);
        imgQw.eq("img_id",img_id);
        //Img nowImg =imgDAO.selectOne(imgQw);
        //String nowImgSrc =nowImg.getImg_src();
        int Art_n =articleDAO.delete(qw);
        int n=imgDAO.delete(imgQw);
        return Art_n;
    }

    //根据id批量删除文章
    public int delMostArticle(JSONArray articleJson) {
        int n = 0;
        JSONArray jsonArray = null;
        jsonArray = new JSONArray(articleJson);
        System.out.println(jsonArray.size());
        for (int i=0;i<jsonArray.size();i++){
            int article_id = (int) jsonArray.getJSONObject(i).get("article_id");
            System.out.println(article_id);
            n = articleDAO.deleteById(article_id);
            System.out.println(n);
        }
        return n;
    }

    //根据id查图片的src
    public String selectImgById(int img_id) {
        Img src = imgDAO.selectById(img_id);
        return src.getImg_src();
    }

}
