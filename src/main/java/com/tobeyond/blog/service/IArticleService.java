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

    PageInfo<ArticleBo> articleList(Integer page, Integer limit, Integer tag_id,Byte is_show);

    ArticleBo articleDetail(Integer id);

    Boolean articleAdd(ArticlePo article,String tagIds);

    Boolean articleDel(Integer id);

    Boolean changeShow(Integer articleId, Byte isShow);

    Boolean articleEditSave(ArticlePo articlePo,String ids);

    List<TagPo> getTagListByArticleId(Integer id);

}
