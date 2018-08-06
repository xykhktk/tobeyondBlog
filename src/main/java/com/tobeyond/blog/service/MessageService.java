package com.tobeyond.blog.service;


import com.tobeyond.blog.dao.domain.ReturnJson;

public interface MessageService {

    ReturnJson insertMessage(String name, String contact, String message);

}
