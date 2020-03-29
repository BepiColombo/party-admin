package com.tck.party.controller;


import com.tck.party.common.base.PageResult;
import com.tck.party.common.base.PartyResponse;
import com.tck.party.common.utils.CodeMsg;
import com.tck.party.entity.Article;
import com.tck.party.service.ArticleService;
import com.tck.party.vo.ArticleQueryParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    ArticleService articleService;


    /**
     * 查询所有文章
     *
     * @param articleQueryParam
     * @return
     */
    @PostMapping(value = "findArticles")
    @RequiresPermissions("article:view")
    public PartyResponse findArticles(@RequestBody ArticleQueryParam articleQueryParam) {
        PageResult<Article> articles = articleService.findArticles(articleQueryParam);
        return new PartyResponse(CodeMsg.SUCCESS.getCode(), CodeMsg.SUCCESS.getMsg(), articles);
    }

    /**
     * 通过id查询文章
     *
     * @return
     */
    @GetMapping(value = "findArticleById")
    @RequiresPermissions("article:view")
    public PartyResponse findArticleById(@RequestParam(value = "articleId") Integer articleId) {
        Article article = articleService.findById(articleId);
        return new PartyResponse(CodeMsg.SUCCESS.getCode(), CodeMsg.SUCCESS.getMsg(), article);
    }

    /**
     * 添加文章
     *
     * @param article
     * @return
     */
    @PostMapping(value = "addArticle")
    @RequiresPermissions("article:add")
    public PartyResponse addArticle(@RequestBody Article article) {
        int res = articleService.insertArticle(article);
        if (res == 1) {
            return new PartyResponse(CodeMsg.SUCCESS.getCode(), "添加成功", "");
        } else {
            return new PartyResponse(CodeMsg.ADD_ACTION_FAIL.getCode(), CodeMsg.ADD_ACTION_FAIL.getMsg(), "");
        }
    }

    /**
     * 编辑文章
     *
     * @param article
     * @return
     */
    @PostMapping(value = "updateArticle")
    @RequiresPermissions("article:update")
    public PartyResponse updateArticle(@RequestBody Article article) {
        int res = articleService.updateArticle(article);
        if (res == 1) {
            return new PartyResponse(CodeMsg.SUCCESS.getCode(), "编辑成功", "");
        } else {
            return new PartyResponse(CodeMsg.UPDATE_ACTION_FAIL.getCode(), CodeMsg.UPDATE_ACTION_FAIL.getMsg(), "");
        }
    }

    /**
     * 删除文章
     *
     * @param data
     * @return
     */
    @PostMapping(value = "deleteArticle")
    @RequiresPermissions("article:delete")
    public PartyResponse deleteArticle(@RequestBody Map<String, Integer> data) {
        int res = articleService.deleteArticle(data.get("articleId"));
        if (res == 1) {
            return new PartyResponse(CodeMsg.SUCCESS.getCode(), "删除成功", "");
        } else {
            return new PartyResponse(CodeMsg.DEL_ACTION_FAIL.getCode(), CodeMsg.DEL_ACTION_FAIL.getMsg(), "");
        }
    }
}
