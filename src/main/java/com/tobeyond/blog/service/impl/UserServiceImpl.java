package com.tobeyond.blog.service.impl;

import com.tobeyond.blog.dao.domain.User;
import com.tobeyond.blog.dao.domain.UserCustom;
import com.tobeyond.blog.dao.domain.UserQueryVo;
import com.tobeyond.blog.dao.mapper.UserMapper;
import com.tobeyond.blog.exception.TobeyondException;
import com.tobeyond.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserMapper userMapper;

    @Override
    public boolean userLogin(String name,String password) {
        User user = userMapper.selectByName(name);
        if(user == null) throw  new TobeyondException("用户名或密码错误");

        return true;
    }

}
