package com.tobeyond.blog.dao.mapper;

import com.tobeyond.blog.model.po.MaximExample;
import com.tobeyond.blog.model.po.MaximPo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MaximMapper {

    @Select("SELECT * FROM maxims WHERE is_show = 1 AND deleted_at IS NULL")
    List<MaximPo> maximList();

    long countByExample(MaximExample example);

//    int deleteByExample(MaximExample example);

    int insert(MaximPo record);

    int insertSelective(MaximPo record);

    List<MaximPo> selectByExample(MaximExample example);

    int updateByExampleSelective(@Param("record") MaximPo record, @Param("example") MaximExample example);

    int updateByExample(@Param("record") MaximPo record, @Param("example") MaximExample example);
}
