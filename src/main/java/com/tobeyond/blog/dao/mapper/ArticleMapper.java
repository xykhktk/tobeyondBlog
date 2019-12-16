package com.tobeyond.blog.dao.mapper;

import com.tobeyond.blog.model.po.ArticlePo;
import com.tobeyond.blog.model.po.ArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface ArticleMapper {
    long countByExample(ArticleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ArticlePo record);

    int insertSelective(ArticlePo record);

    List<ArticlePo> selectByExampleWithBLOBs(ArticleExample example);

    List<ArticlePo> selectByExample(ArticleExample example);

    ArticlePo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ArticlePo record, @Param("example") ArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") ArticlePo record, @Param("example") ArticleExample example);

    int updateByExample(@Param("record") ArticlePo record, @Param("example") ArticleExample example);

    int updateByPrimaryKeySelective(ArticlePo record);

    int updateByPrimaryKeyWithBLOBs(ArticlePo record);

    int updateByPrimaryKey(ArticlePo record);
}