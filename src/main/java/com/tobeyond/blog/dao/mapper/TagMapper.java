package com.tobeyond.blog.dao.mapper;

import com.tobeyond.blog.model.po.TagExample;
import com.tobeyond.blog.model.po.TagPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagMapper {

//    @Select("select * from tags where id = #{id}")
//    TagPo findTagById(long id);

//    List<TagBo> tagsList();

    long countByExample(TagExample example);

    int insert(TagPo record);

    int insertSelective(TagPo record);

    List<TagPo> selectByExample(TagExample example);

    int updateByExampleSelective(@Param("record") TagPo record, @Param("example") TagExample example);

    int updateByExample(@Param("record") TagPo record, @Param("example") TagExample example);
}
