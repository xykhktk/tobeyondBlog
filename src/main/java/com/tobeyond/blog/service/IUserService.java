package com.tobeyond.blog.service;



import com.tobeyond.blog.model.bo.UserCustom;

public interface IUserService {


    UserCustom userLogin(String name, String password);

    UserCustom findUserById(Integer id);

}
