package com.tobeyond.blog.controller.admin.api;

import com.tobeyond.blog.annotation.AdminLoginToken;
import com.tobeyond.blog.model.dto.ReturnJson;
import com.tobeyond.blog.mq.MqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@AdminLoginToken
@RestController("apiAdminRabbitMqController")
@RequestMapping(value = "/api/admin/rabbitmq")
public class RabbitMqController extends BaseController{

    @Autowired
    MqSender mqSender;

    @PostMapping(value = "/send")
    public ReturnJson list(@RequestParam(value = "message",required = true) String message){
        mqSender.send(message);
        return  ReturnJson.success("发送数据成功",returnData);
    }


}
