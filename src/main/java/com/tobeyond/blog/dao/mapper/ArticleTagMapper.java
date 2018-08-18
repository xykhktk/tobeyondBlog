package com.tobeyond.blog.dao.mapper;

import com.tobeyond.blog.model.po.ArticleTagPo;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

public interface ArticleTagMapper {

    @Select("select * from article_tags where article_id = #{id}")
    @Results({
            @Result(column = "tag_id",property = "tag", //article_tag下的tag_id将是空的，如果要取tag_id，需要取article_tag下的tag的id
            one = @One(select = "com.tobeyond.blog.dao.mapper.TagMapper.findTagById"))
    })
    List<ArticleTagPo> findTagsByArticle(Long id);

    @Select("select * from article_tags where tag_id=#{tag_id}")
    List<ArticleTagPo> getTagsListByTagId(Long tag_id);

    int insertBatch(List<ArticleTagPo> list);

}
