package com.tobeyond.blog.service.impl;

import com.tobeyond.blog.model.dto.ReturnJson;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl {

    protected ReturnJson returnJson = null;

    BaseServiceImpl(){
        super();
        if(returnJson == null){
            returnJson = new ReturnJson();
        }
    }

    ReturnJson setSuccess(String code, String message, Object data){
        returnJson.setSuccess(true);
        returnJson.setData(data);
        returnJson.setMessage(message);
        returnJson.setCode(code);
        return returnJson;
    }

    ReturnJson setfail(String code,String message,Object data){
        returnJson.setSuccess(false);
        returnJson.setData(data);
        returnJson.setMessage(message);
        returnJson.setCode(code);
        return returnJson;
    }
}
