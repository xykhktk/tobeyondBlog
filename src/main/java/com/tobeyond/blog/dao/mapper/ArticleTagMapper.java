package com.tobeyond.blog.dao.mapper;

import com.tobeyond.blog.model.po.ArticleTag;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleTagMapper {

    @Select("select * from article_tags where article_id = #{id}")
    @Results({
            @Result(column = "tag_id",property = "tag", //article_tag下的tag_id将是空的，如果要取tag_id，需要取article_tag下的tag的id
            one = @One(select = "com.tobeyond.blog.dao.mapper.TagMapper.findTagById"))
    })
    List<ArticleTag> findTagsByArticle(Long id);

    @Select("select * from article_tags where tag_id=#{tag_id}")
    List<ArticleTag> getTagsListByTagId(Long tag_id);

}
