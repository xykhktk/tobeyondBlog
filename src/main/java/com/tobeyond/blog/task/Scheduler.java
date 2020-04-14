package com.tobeyond.blog.task;

import com.tobeyond.blog.mq.MqListener;
import com.tobeyond.blog.mq.MqSender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    MqSender mqSender;

    /**
     * 只是一个示例
     * 入口类添加  @EnableScheduling ,定时任务类添加@Component , 定时类内部的方法添加 @Scheduled ,就可以实现定时任务
     *
     */
//    @Scheduled(fixedRate = 2000)
//    public void task1(){
//        System.out.println("task1");
//        mqSender.send();
//    }

//    @Scheduled(cron = "05")
//    public void task2(){
//
//    }

}
