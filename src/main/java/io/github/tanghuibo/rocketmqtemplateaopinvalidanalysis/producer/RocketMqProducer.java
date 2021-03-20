package io.github.tanghuibo.rocketmqtemplateaopinvalidanalysis.producer;

import io.github.tanghuibo.rocketmqtemplateaopinvalidanalysis.message.CommonMessage;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tanghuibo
 * @date 2021/3/20下午2:45
 */
@Service
public class RocketMqProducer {

    @Resource
    RocketMQTemplate rocketMqTemplate;

    @Value("${rocketmq.producer.topic}")
    private String topic;

    public void send(String message) {
        CommonMessage commonMessage = new CommonMessage();
        commonMessage.setMessage(message);
        rocketMqTemplate.send(topic, MessageBuilder.withPayload(commonMessage).build());
    }
}
