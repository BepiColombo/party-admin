package com.tck.party.mapper;


import com.tck.party.entity.Article;
import com.tck.party.vo.ArticleQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {

    List<Article> findArticles(ArticleQueryParam articleQueryParam);

    Article findById(Integer articleId);

    int insertArticle(Article article);

    int updateArticle(Article article);

    int deleteArticle(Integer articleId);

}
