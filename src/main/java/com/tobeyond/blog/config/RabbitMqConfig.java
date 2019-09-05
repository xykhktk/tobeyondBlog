package com.tobeyond.blog.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;


@Configuration
public class RabbitMqConfig {

//    @Resource
//    private RabbitTemplate rabbitTemplate;

    @Bean
    public Queue helloQueue() {
        return new Queue("queue-test");
    }

    /**
     * 定制化amqp模版
     * <p>
     * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调   即消息发送到exchange  ack
     * ReturnCallback接口用于实现消息发送到RabbitMQ 交换器，但无相应队列与交换器绑定时的回调  即消息发送不到任何一个队列中  ack
     */
//    @Bean
//    public RabbitTemplate rabbitTemplate() {
//
//        // 消息发送失败返回到队列中, yml需要配置 publisher-returns: true
//        rabbitTemplate.setMandatory(true);
//
//        // 消息返回, yml需要配置 publisher-returns: true
//        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
//            String correlationId = message.getMessageProperties().getCorrelationIdString();
//        });
//
//        // 消息确认, yml需要配置 publisher-confirms: true
//        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
//            if (ack) {
//                System.out.println("消息发送到exchange成功,id:" + correlationData.getId());
//            } else {
//                System.out.println("消息发送到exchange失败");
//            }
//        });
//
//        return rabbitTemplate;
//    }

}
