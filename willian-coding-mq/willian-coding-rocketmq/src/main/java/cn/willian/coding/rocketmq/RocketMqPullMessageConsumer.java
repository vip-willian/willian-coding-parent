package cn.willian.coding.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 拉模式消费
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-08 23:43:33
 */
@Slf4j
public class RocketMqPullMessageConsumer {

    private static final String NAME_SERVER_ADDRESS = "localhost:9876";

    public static void main(String[] args) throws Exception {

        RocketMqPullMessageConsumer messageConsumer = new RocketMqPullMessageConsumer();
        messageConsumer.consume();
    }

    public void consume() throws Exception {

        // 构造Consumer时，必须指定groupId
        DefaultLitePullConsumer consumer = new DefaultLitePullConsumer("willian_consumer_group_1");
        consumer.setNamesrvAddr(NAME_SERVER_ADDRESS);
        // nameServer地址,用于获取broker、topic信息
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // 指定订阅的主题与tag，通过tag可以定制性消费（*表示全部tag）
        consumer.subscribe("rocketmq_topic_test", "*");
        consumer.start();
        try {
            // 5、循环开始消费消息
            while (true) {
                // 拉取消息，无消息时会阻塞 (默认会阻塞5s, 没有消息则返回一个空集合)
                List<MessageExt> messageExts = consumer.poll();
                System.out.printf("%s messageExts.size(): %s %n", System.currentTimeMillis(), messageExts.size());
                messageExts.forEach(msg -> {
                    // 业务逻辑
                    System.out.printf("%s Receive New Messages, body: %s %n", Thread.currentThread().getName(), new String(msg.getBody()));
                });
                // 同步消费位置。不执行该方法，应用重启会存在重复消费。
                if (!messageExts.isEmpty()) {
                    consumer.commit();
                }
            }
        } finally {
            consumer.shutdown();
        }
    }
}
