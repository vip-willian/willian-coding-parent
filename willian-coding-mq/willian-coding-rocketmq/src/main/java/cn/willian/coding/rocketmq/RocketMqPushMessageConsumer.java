package cn.willian.coding.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 推模式消费
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-08 23:43:33
 */
@Slf4j
public class RocketMqPushMessageConsumer {

    private static final String NAME_SERVER_ADDRESS = "localhost:9876";

    public static void main(String[] args) throws Exception {

        RocketMqPushMessageConsumer messageConsumer = new RocketMqPushMessageConsumer();
        messageConsumer.consume();
    }

    public void consume() throws Exception {

        // 构造Consumer时，必须指定groupId
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("willian_consumer_group_2");
        consumer.setNamesrvAddr(NAME_SERVER_ADDRESS);
        // 指定订阅的主题与tag，通过tag可以定制性消费（*表示全部tag）
        consumer.subscribe("rocketmq_topic_test", "*");
        consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
            System.out.println("queueId:" + list.get(0).getQueueId());
            System.out.println("ThreadName:" + Thread.currentThread().getName());
            for (MessageExt message : list) {
                System.out.println("Message:" + new String(message.getBody()));
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
    }
}
