package com.tobeyond.blog.service.impl;

import com.tobeyond.blog.dao.domain.User;
import com.tobeyond.blog.dao.domain.UserCustom;
import com.tobeyond.blog.dao.mapper.UserMapper;
import com.tobeyond.blog.exception.TobeyondException;
import com.tobeyond.blog.service.UserService;
import com.tobeyond.blog.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserMapper userMapper;

    @Override
    public UserCustom userLogin(String name, String password) {
        UserCustom user = userMapper.selectByName(name);
        if(user == null) throw  new TobeyondException("用户名不存在");

        StringBuffer p = new StringBuffer(password);
        p.append(user.getSalt());
        String password_add_salt = CommonUtils.MD5encode(p.toString());
        if(!user.getPassword().equals(password_add_salt)) throw  new TobeyondException("用户名或密码错误");

        return user;
    }

}
