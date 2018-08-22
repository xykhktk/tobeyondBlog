package com.tobeyond.blog.dao.mapper;

import com.tobeyond.blog.model.Bo.TagBo;
import com.tobeyond.blog.model.po.TagPo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TagMapper {

    @Select("select * from tags where id = #{id}")
    TagPo findTagById(long id);


    List<TagBo> tagsList();
}
