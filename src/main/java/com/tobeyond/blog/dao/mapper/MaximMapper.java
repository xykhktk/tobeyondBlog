package com.tobeyond.blog.dao.mapper;

import com.tobeyond.blog.model.po.MaximPo;
import com.tobeyond.blog.model.po.MaximExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaximMapper {
    long countByExample(MaximExample example);

//    int deleteByPrimaryKey(Integer id);

    int insert(MaximPo record);

    int insertSelective(MaximPo record);

    List<MaximPo> selectByExample(MaximExample example);

    MaximPo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MaximPo record, @Param("example") MaximExample example);

    int updateByExample(@Param("record") MaximPo record, @Param("example") MaximExample example);

    int updateByPrimaryKeySelective(MaximPo record);

    int updateByPrimaryKey(MaximPo record);
}