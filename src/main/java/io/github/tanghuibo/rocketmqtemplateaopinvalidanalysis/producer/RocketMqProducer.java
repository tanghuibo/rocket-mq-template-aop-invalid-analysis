package io.github.tanghuibo.rocketmqtemplateaopinvalidanalysis.producer;

import io.github.tanghuibo.rocketmqtemplateaopinvalidanalysis.message.CommonMessage;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author tanghuibo
 * @date 2021/3/20下午2:45
 */
@Service
public class RocketMqProducer {

    Logger log = LoggerFactory.getLogger(RocketMqProducer.class);

    @Resource
    RocketMQTemplate rocketMqTemplate;

    @Resource
    AbstractAdvisorAutoProxyCreator abstractAdvisorAutoProxyCreator;

    @Value("${rocketMqTemplateEnableProxy:false}")
    Boolean rocketMqTemplateEnableProxy;

    @PostConstruct
    public void init() {
        //手动添加后置处理
        if(rocketMqTemplateEnableProxy) {
            rocketMqTemplate = (RocketMQTemplate)abstractAdvisorAutoProxyCreator.postProcessAfterInitialization(rocketMqTemplate, "rocketMqTemplate");
        }
    }

    @Value("${rocketmq.producer.topic}")
    private String topic;

    public void send(String message) {
        CommonMessage commonMessage = new CommonMessage();
        commonMessage.setMessage(message);
        log.info("start send message...");
        rocketMqTemplate.send(topic, MessageBuilder.withPayload(commonMessage).build());
        log.info("end send message");
    }
}
