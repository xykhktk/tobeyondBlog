package com.tobeyond.blog.service;


import com.tobeyond.blog.model.Dto.ReturnJson;

public interface MessageService {

    ReturnJson insertMessage(String name, String contact, String message);

}
