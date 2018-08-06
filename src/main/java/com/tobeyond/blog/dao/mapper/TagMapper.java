package com.tobeyond.blog.dao.mapper;

import com.tobeyond.blog.dao.domain.Tag;
import org.apache.ibatis.annotations.Select;

public interface TagMapper {

    @Select("select * from tags where id = #{id}")
    Tag findTagById(long id);
}
