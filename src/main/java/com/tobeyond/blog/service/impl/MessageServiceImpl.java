package com.tobeyond.blog.service.impl;

import com.tobeyond.blog.dao.domain.Message;
import com.tobeyond.blog.model.Dto.ReturnJson;
import com.tobeyond.blog.dao.mapper.MessageMapper;
import com.tobeyond.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MessageServiceImpl extends BaseServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Override
    public ReturnJson insertMessage(String name, String contact, String message) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Message messageObj = new Message();
        messageObj.setContact(contact);
        messageObj.setMessage(message);
        messageObj.setName(name);
        messageObj.setCreated_at(df.format(new Date()));

        int res = messageMapper.insertMessage(messageObj);
        returnJson = setfail("400","留言失败","");
        if(res > 0) returnJson = setSuccess("200","留言成功","");
        return returnJson;
    }




}
