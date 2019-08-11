package com.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
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
        int nowPage =(count%limit==0)? count/limit:count/limit+1 ;
        //越界判断
        if (page<=0){
            page =0;
        }if (page>nowPage){
            page =nowPage;
        }
        //Page p = new Page(page,limit);
        //qw.last("limit"+page+","+limit);
        List<Article> articleList =articleDAO.getAllArticle((page-1)*limit,page*limit);
        return articleList;
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

}
