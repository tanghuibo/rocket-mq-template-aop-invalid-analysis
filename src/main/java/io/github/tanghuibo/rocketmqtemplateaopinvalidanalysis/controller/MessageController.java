package io.github.tanghuibo.rocketmqtemplateaopinvalidanalysis.controller;

import io.github.tanghuibo.rocketmqtemplateaopinvalidanalysis.producer.RocketMqProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tanghuibo
 * @date 2021/3/20下午2:42
 */
@RestController
@RequestMapping("message")
public class MessageController {
    @Resource
    RocketMqProducer producer;


    @GetMapping("send")
    public String send(@RequestParam("message") String message) {
        producer.send(message);
        return "ok";
    }
}
