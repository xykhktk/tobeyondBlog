package com.tobeyond.blog.service;



import com.tobeyond.blog.model.Bo.UserCustom;

public interface UserService {


    UserCustom userLogin(String name, String password);

}
