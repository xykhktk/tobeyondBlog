package com.tobeyond.blog.controller.admin.api;

import com.tobeyond.blog.annotation.AdminLoginToken;
import com.tobeyond.blog.model.bo.TaskInformationsBo;
import com.tobeyond.blog.model.dto.ReturnJson;
import com.tobeyond.blog.service.IQuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AdminLoginToken
@RestController("apiAdminTaskController")
@RequestMapping(value = "/api/admin/task")
public class TaskController extends BaseController{

    @Autowired
    IQuartzService quartzService;

    @PostMapping(value = "/list")
    public ReturnJson list(){
        List<TaskInformationsBo> taskList =  quartzService.getList();
        returnData.put("taskList",taskList);
        return  ReturnJson.success("获取数据成功",returnData);
    }
}
