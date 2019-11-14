package com.tobeyond.blog.controller.admin.api;

import com.tobeyond.blog.model.Dto.ReturnJson;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    protected ReturnJson returnJson;
    protected Map returnData;

    BaseController(){
        returnJson = ReturnJson.success("获取数据成功");
        returnData = new HashMap<String,String>();
    }
}
