package io.github.tanghuibo.rocketmqtemplateaopinvalidanalysis.producer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
/**
 * @author tanghuibo
 * @date 2021/3/20下午2:56
 */
@SpringBootTest
public class RocketMqProducerTest {

    @Resource
    RocketMqProducer rocketMqProducer;

    @Test
    public void send() {
        rocketMqProducer.send("message test");
    }
}