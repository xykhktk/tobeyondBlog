package com.tobeyond.blog.dao.mapper;

import com.tobeyond.blog.model.po.TagPo;
import com.tobeyond.blog.model.po.TagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagMapper {

    long countByExample(TagExample example);

//    int deleteByPrimaryKey(Integer id);

    int insert(TagPo record);

    int insertSelective(TagPo record);

    List<TagPo> selectByExample(TagExample example);

    TagPo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TagPo record, @Param("example") TagExample example);

    int updateByExample(@Param("record") TagPo record, @Param("example") TagExample example);

    int updateByPrimaryKeySelective(TagPo record);

    int updateByPrimaryKey(TagPo record);
}