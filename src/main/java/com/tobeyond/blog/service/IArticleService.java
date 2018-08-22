package com.tobeyond.blog.service;


import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.model.Bo.ArticleBo;
import com.tobeyond.blog.model.po.ArticlePo;
import com.tobeyond.blog.model.po.ArticleTagPo;

import java.util.List;

public interface IArticleService {

    List<ArticlePo> articleList(Long tag_id);

    List<ArticlePo> articleListForIndex();

    ArticlePo getArticleById(long id);

    List<ArticleTagPo> getTagListByTagId(Long tag_id);

    PageInfo<ArticlePo> articleListBaseInfo(Integer page, Integer limit);

    ArticleBo articleFullInfo(Long id);

    Boolean articleAdd(ArticlePo article,String tagIds);

    Boolean articleDel(Integer id);

    Boolean changeShow(Integer id,Integer is_show);

    Boolean articleEditSave(Integer id,ArticlePo articlePo,String ids);

}
