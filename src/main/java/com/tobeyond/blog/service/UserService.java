package com.tobeyond.blog.service;



import com.tobeyond.blog.dao.domain.UserCustom;

import java.util.List;

public interface UserService {


    UserCustom userLogin(String name, String password);

}
