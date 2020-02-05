package com.tobeyond.blog.controller;

import com.tobeyond.blog.model.dto.ReturnJson;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

public class ApiBaseController {

    protected ReturnJson returnJson;
    protected Map returnData;

    ApiBaseController(){
        returnJson = ReturnJson.success("获取数据成功");
        returnData = new HashMap<String,String>();
    }

    @ModelAttribute
    public void init(){
        returnData.clear();
    }
}
