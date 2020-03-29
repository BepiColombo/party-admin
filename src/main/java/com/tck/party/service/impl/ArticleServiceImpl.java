package com.tck.party.service.impl;

import com.github.pagehelper.PageHelper;
import com.tck.party.common.base.PageResult;
import com.tck.party.entity.Article;
import com.tck.party.mapper.ArticleMapper;
import com.tck.party.service.ArticleService;
import com.tck.party.vo.ArticleQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    /**
     * 查找所有的文章
     * @param articleQueryParam
     * @return
     */
    @Override
    public PageResult<Article> findArticles(ArticleQueryParam articleQueryParam) {
        PageHelper.startPage(articleQueryParam.getPageNum(),articleQueryParam.getPageSize());
        System.out.println(articleQueryParam.getPageNum());
        List<Article> res = articleMapper.findArticles(articleQueryParam);
        System.out.println(res);
        PageResult<Article> pageResult= new PageResult<Article>(res);
        return pageResult;
    }

    /**
     * 通过id查找文章
     * @param articleId
     * @return
     */
    @Override
    public Article findById(Integer articleId){
        Article article= articleMapper.findById(articleId);
        return article;
    }

    @Override
    public int insertArticle(Article article) {
        return articleMapper.insertArticle(article);
    }

    @Override
    public int updateArticle(Article article) {
        return articleMapper.updateArticle(article);
    }

    @Override
    public int deleteArticle(Integer articleId) {
        return articleMapper.deleteArticle(articleId);
    }
}
