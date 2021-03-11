package com.example.demo.RocketMQ;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 生产者
 */
@RestController
public class Producer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("send")
    public void send(){
        rocketMQTemplate.convertAndSend("first-topic","你好,Java旅途");
    }
}
