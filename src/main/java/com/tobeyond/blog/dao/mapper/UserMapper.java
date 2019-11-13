package com.tobeyond.blog.dao.mapper;

import com.tobeyond.blog.model.Bo.UserCustom;

public interface UserMapper {

    UserCustom selectByName(String name);

    UserCustom selectById(Integer id);
}
