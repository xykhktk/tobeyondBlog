package com.tobeyond.blog.dao.mapper;

import com.tobeyond.blog.model.po.ArticleTagsExample;
import com.tobeyond.blog.model.po.ArticleTagsPo;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

public interface ArticleTagsMapper {

    @Select("select * from article_tags where article_id = #{id}")
    @Results({
            @Result(column = "tag_id",property = "tag", //article_tag下的tag_id将是空的，如果要取tag_id，需要取article_tag下的tag的id
            one = @One(select = "com.tobeyond.blog.dao.mapper.TagMapper.findTagById"))
    })
    List<ArticleTagsPo> findTagsByArticle(Long id);

    @Select("select * from article_tags where tag_id=#{tag_id}")
    List<ArticleTagsPo> getTagsListByTagId(Long tag_id);

    int insertBatch(List<ArticleTagsPo> list);

    int delArticleTags(HashMap<String,Object> params);

//    以下是代码生成器生成

    long countByExample(ArticleTagsExample example);

    int insert(ArticleTagsPo record);

    int insertSelective(ArticleTagsPo record);

    List<ArticleTagsPo> selectByExample(ArticleTagsExample example);

    int updateByExampleSelective(@Param("record") ArticleTagsPo record, @Param("example") ArticleTagsExample example);

    int updateByExample(@Param("record") ArticleTagsPo record, @Param("example") ArticleTagsExample example);

}
