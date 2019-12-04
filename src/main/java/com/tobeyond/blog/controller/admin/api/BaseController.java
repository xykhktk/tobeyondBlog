package com.tobeyond.blog.controller.admin.api;

import com.tobeyond.blog.model.dto.ReturnJson;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    protected ReturnJson returnJson;
    protected Map returnData;

    BaseController(){
        returnJson = ReturnJson.success("获取数据成功");
        returnData = new HashMap<String,String>();
    }

    @ModelAttribute
    public void init(){
        returnData.clear();
    }
}
