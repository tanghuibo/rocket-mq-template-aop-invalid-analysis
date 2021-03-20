package io.github.tanghuibo.rocketmqtemplateaopinvalidanalysis.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @author tanghuibo
 * @date 2021/3/20下午3:00
 */
@Component
@RocketMQMessageListener(
        nameServer = "${rocketmq.name-server}",
        consumerGroup = "${rocketmq.consumer.group}",
        topic = "${rocketmq.consumer.topic}"
)
public class RocketMqConsumer implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {

    Logger log = LoggerFactory.getLogger(RocketMqConsumer.class);

    @Override
    public void onMessage(MessageExt messageExt) {
        log.info("consumer message: id:{}; data: {}", messageExt.getMsgId(), new String(messageExt.getBody(), StandardCharsets.UTF_8));
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        defaultMQPushConsumer.setInstanceName(UUID.randomUUID().toString());
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
    }
}
