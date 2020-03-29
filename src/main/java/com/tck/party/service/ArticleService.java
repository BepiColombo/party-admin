package com.tck.party.service;

import com.tck.party.common.base.PageResult;
import com.tck.party.entity.Article;
import com.tck.party.vo.ArticleQueryParam;

public interface ArticleService {

    PageResult<Article> findArticles(ArticleQueryParam articleQueryParam);

    Article findById(Integer articleId);

    int insertArticle(Article article);

    int updateArticle(Article article);

    int deleteArticle(Integer articleId);
}
