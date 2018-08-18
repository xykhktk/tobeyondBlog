package com.tobeyond.blog.service;


import com.tobeyond.blog.model.Dto.ReturnJson;

public interface IMessageService {

    ReturnJson insertMessage(String name, String contact, String message);

}
