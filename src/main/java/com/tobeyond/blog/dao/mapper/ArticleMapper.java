package com.tobeyond.blog.dao.mapper;

import com.tobeyond.blog.model.Bo.ArticleBo;
import com.tobeyond.blog.model.Bo.ArticleTagBo;
import com.tobeyond.blog.model.po.ArticlePo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public interface ArticleMapper {


    @SelectProvider(type = com.tobeyond.blog.dao.provider.ArticleProvider.class,method = "articleList")
    @Results({
            @Result(id = true, column = "id", property = "article_id"), //在数据库字段名是id,希望取出来是article_id.注意bean里面也要对应有article_id
//            @Result(column = "title", property = "title"),
//            @Result(column = "subtitle", property = "subtitle"),
//            @Result(column = "content", property = "content"),
//            @Result(column = "page_image", property = "page_image"),
//            @Result(column = "is_original", property = "is_original"),
//            @Result(column = "created_at", property = "created_at"),
//            @Result(column = "updated_at", property = "updated_at"),
            @Result(column = "id", property = "articleTag", //将id作为参数传入findTagsByArticle
                    many = @Many(
                            select = "com.tobeyond.blog.dao.mapper.ArticleTagMapper.findTagsByArticle"
//                            fetchType = FetchType.LAZY  //??? 加了这段会有问题.待研究
                    )
            )
    })
    List<ArticlePo> articleList(@Param("in_ids") String in_ids);

    @Select("select * from articles where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "article_id"),
            @Result(column = "id", property = "articleTag",
                    many = @Many(select = "com.tobeyond.blog.dao.mapper.ArticleTagMapper.findTagsByArticle")
            )})
    ArticlePo getArticleById(Long id);

    @Select("select * from articles order by created_at limit 4")
    @Results({
            @Result(id = true, column = "id", property = "article_id"),
            @Result(column = "id", property = "articleTag",
                    many = @Many(
                            select = "com.tobeyond.blog.dao.mapper.ArticleTagMapper.findTagsByArticle"
                    )
            )
    })
    List<ArticlePo> articleListForIndex();

    List<ArticleBo> articleListBaseInfo(HashMap<String,Object> params);

    ArticleBo articleFullInfo(HashMap<String,Object> params);

    int articleAdd(ArticlePo article);

    int articleDel(Integer id);

    int changeShow(HashMap<String,Object> param);

    int articleUpdate(ArticlePo articlePo);

    ArrayList<ArticleTagBo> getArticleTags(Integer id);
}
