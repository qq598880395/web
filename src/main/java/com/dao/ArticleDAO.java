package com.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pojo.Article;

import java.util.List;

public interface ArticleDAO extends BaseMapper<Article> {

    public List<Article> getAllArticle(Integer page,Integer limit);
    public int getArticleCount();
}
