package com.service;

import com.dao.ArticleDAO;
import com.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    private ArticleDAO articleDAO;


    /**
     *
     * @param article_title
     * @param article_text
     * @param img_id 与img表img_id外键绑定
     * @return
     */
    public int addArticle(String article_title,String article_text,Integer img_id){
        Article article =new Article();
        article.setArticle_title(article_title);
        article.setArticle_text(article_text);
        article.setImg_id(img_id);
        int n=articleDAO.insert(article);
       return n;
    }
}
