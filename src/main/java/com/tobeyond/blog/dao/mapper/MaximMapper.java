package com.tobeyond.blog.dao.mapper;

import com.tobeyond.blog.model.po.Maxim;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MaximMapper {

    @Select("SELECT * FROM maxims WHERE is_show = 1 AND deleted_at IS NULL")
    List<Maxim> maximList();
}
