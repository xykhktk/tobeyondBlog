package com.tobeyond.blog.service;


import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.model.bo.ArticleBo;
import com.tobeyond.blog.model.po.ArticlePo;
import com.tobeyond.blog.model.po.ArticleTagsPo;
import com.tobeyond.blog.model.po.TagPo;

import java.util.List;

public interface IArticleService {

//    List<ArticlePo> articleList(Long tag_id);

//    List<ArticlePo> articleListForIndex();

//    ArticlePo getArticleById(long id);

    List<ArticleTagsPo> getTagListByTagId(Long tag_id);

    PageInfo<ArticleBo> articleListBaseInfo(Integer page, Integer limit, Long tag_id,Boolean is_show);

    ArticleBo articleFullInfo(Long id);

    Boolean articleAdd(ArticlePo article,String tagIds);

    Boolean articleDel(Integer id);

    Boolean changeShow(Integer id,Integer is_show);

    Boolean articleEditSave(ArticlePo articlePo,String ids);

    List<TagPo> getTagListByArticleId(Integer id);

}
