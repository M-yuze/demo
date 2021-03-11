package com.example.demo.RocketMQ;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 */
@Component
@RocketMQMessageListener(topic = "first-topic",consumerGroup = "my-consumer-group")
@Slf4j
public class Consumer implements RocketMQListener {

    @Override
    public void onMessage(Object msg) {
        log.info("我收到消息了,消息内容为：" + msg);
    }
}
