package com.tobeyond.blog.controller.admin.pc;

import com.tobeyond.blog.model.dto.ReturnJson;
import com.tobeyond.blog.service.IQuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("taskController")
@RequestMapping(value = "admin/task")
public class TaskController {

    @Autowired
    IQuartzService quartzService;

    @RequestMapping(value = "/stopAll")
    public ReturnJson stopAll(){
        quartzService.getList();
        return  ReturnJson.success("停止所有任务成功");
    }


}
