package com.tobeyond.blog.dao.mapper;

import com.tobeyond.blog.model.Bo.UserCustom;
import com.tobeyond.blog.model.po.User;

public interface UserMapper {

    UserCustom selectByName(String name);

    UserCustom selectById(Integer id);

    int updateTokenTimeById(User user);
}
