package com.tobeyond.blog.service;


import com.tobeyond.blog.dao.domain.Article;
import com.tobeyond.blog.dao.domain.ArticleTag;

import java.util.List;

public interface ArticleService {

    List<Article> articleList(Long tag_id);

    List<Article> articleListForIndex();

    Article getArticleById(long id);

    List<ArticleTag> getTagListByTagId(Long tag_id);

}
