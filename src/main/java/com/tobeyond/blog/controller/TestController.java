package com.tobeyond.blog.controller;

import com.tobeyond.blog.model.Dto.ReturnJson;
import com.tobeyond.blog.service.IQuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("testController")
@RequestMapping(value = "test")
public class TestController {

    @Autowired
    IQuartzService quartzService;

    @RequestMapping(value = "/stopAll")
    @ResponseBody
    public ReturnJson stopAll(){
        quartzService.getList(1);
        return  ReturnJson.success("停止所有任务成功");
    }


}
