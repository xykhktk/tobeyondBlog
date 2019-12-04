package com.tobeyond.blog.controller;

import com.tobeyond.blog.model.dto.ReturnJson;
import com.tobeyond.blog.model.vo.TaskInformationsVo;
import com.tobeyond.blog.service.IQuartzService;
import org.apache.ibatis.annotations.Param;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("testController")
@RequestMapping(value = "test")
public class TestController {

    @Autowired
    IQuartzService quartzService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public ReturnJson list(){
        List<TaskInformationsVo> list = quartzService.getList();
        return  ReturnJson.success("成功",list);
    }

    @RequestMapping(value = "/pauseJob")
    @ResponseBody
    public ReturnJson pauseJob(@Param(value = "jobName") String jobName,
                               @Param(value = "jobGroup") String jobGroup
    ) {
        try {
            quartzService.pauseJob(jobName,jobGroup);
        } catch (SchedulerException e) {
            System.out.println(e.getMessage());
            return ReturnJson.error(e.getMessage());
        }
        return ReturnJson.success("停止任务成功");
    }

    @RequestMapping(value = "/resumeJob")
    @ResponseBody
    public ReturnJson resumeJob(@Param(value = "jobName") String jobName,
                               @Param(value = "jobGroup") String jobGroup
    ) {
        try {
            quartzService.resumeJob(jobName,jobGroup);
        } catch (SchedulerException e) {
            System.out.println(e.getMessage());
            return ReturnJson.error(e.getMessage());
        }
        return ReturnJson.success("启动任务成功");
    }

//    @RequestMapping(value = "/list")
//    public ModelAndView list(){
//        List<TaskInformationsVo> list = quartzService.getList();
//        ModelAndView modelAndView = new ModelAndView("/admin/quartz/list");
//        modelAndView.addObject("list",list);
//        return modelAndView;
//    }

}
