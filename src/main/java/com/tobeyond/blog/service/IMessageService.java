package com.tobeyond.blog.service;


import com.tobeyond.blog.model.dto.ReturnJson;

public interface IMessageService {

    ReturnJson insertMessage(String name, String contact, String message);

}
