package io.github.tanghuibo.rocketmqtemplateaopinvalidanalysis.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author tanghuibo
 * @date 2021/3/20下午3:44
 */
@Aspect
@Component
public class RocketMqLogAspect {

    Logger log = LoggerFactory.getLogger(RocketMqLogAspect.class);
    @After("execution(void org.springframework.messaging.core.AbstractMessageSendingTemplate.send(..))")
    public void log(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if(args.length == 1) {
            if(args[0] instanceof Message) {
                log.info("rocketmq send message: {}", JSON.toJSONString(((Message)args[0]).getPayload()));
            }
        } else if(args.length == 2) {
            if(args[1] instanceof Message) {
                log.info("rocketmq send message: topic {}, data: {}", args[0], JSON.toJSONString(((Message)args[1]).getPayload()));
            }
        }
    }

}
