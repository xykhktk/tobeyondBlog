package com.tobeyond.blog.service;



import com.tobeyond.blog.model.Bo.UserCustom;

public interface IUserService {


    UserCustom userLogin(String name, String password);

    UserCustom findUserById(Integer id);

}
