package com.tobeyond.blog.service;



import com.tobeyond.blog.dao.domain.User;

import java.util.List;

public interface UserService {


    User userLogin(String name, String password);

}
