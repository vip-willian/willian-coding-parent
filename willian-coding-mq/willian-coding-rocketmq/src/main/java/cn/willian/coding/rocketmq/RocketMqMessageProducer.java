package cn.willian.coding.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-08 23:43:33
 */
public class RocketMqMessageProducer {

    private static final String NAME_SERVER_ADDRESS = "localhost:9876";
    private final DefaultMQProducer producer;

    public RocketMqMessageProducer() throws MQClientException {

        producer = new DefaultMQProducer("willian_producer_group_1");
        producer.setNamesrvAddr(NAME_SERVER_ADDRESS);
        producer.start();
    }

    public static void main(String[] args) throws Exception {

        RocketMqMessageProducer messageProducer = new RocketMqMessageProducer();
        // 发送10条消息
        for (int i = 0; i < 10; i++) {
            messageProducer.sendMessage("hello rocketmq " + i);
        }
        messageProducer.shutdown();
    }

    private void shutdown() {
        producer.shutdown();
    }

    public void sendMessage(String content) throws Exception {

        Message message = new Message();
        message.setTopic("rocketmq_topic_test");
        message.setTags("*");
        message.setBody(content.getBytes(StandardCharsets.UTF_8));
        SendResult result = producer.send(message);
        System.out.println(result.getMsgId());
        SendStatus sendStatus = result.getSendStatus();
        System.out.println(sendStatus);

//        SEND_OK,
//        FLUSH_DISK_TIMEOUT, 表示没有在规定时间内完成刷盘（需要Broker 的刷盘策Ill创立设置成 SYNC_FLUSH 才会报这个错误）
//        FLUSH_SLAVE_TIMEOUT, 表示在主备方式下，并且Broker 被设置成SYNC_MASTER 方式，没有在设定时间内完成主从同步。
//        SLAVE_NOT_AVAILABLE; 这个状态产生的场景和FLUSH_SLAVE_TIMEOUT 类似， 表示在主备方式下，并且Broker 被设置成SYNC_MASTER ，但是没有找到被配置成Slave 的Broker
    }
}
