package com.tobeyond.blog.dao.mapper;

import com.tobeyond.blog.dao.domain.User;
import com.tobeyond.blog.dao.domain.UserCustom;
import com.tobeyond.blog.dao.domain.UserQueryVo;
import org.springframework.stereotype.Component;

import java.util.List;

public interface UserMapper {

    UserCustom selectByName(String name);
}
