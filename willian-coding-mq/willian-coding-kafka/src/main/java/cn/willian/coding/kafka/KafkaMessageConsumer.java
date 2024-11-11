package cn.willian.coding.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-08 14:26:36
 */
public class KafkaMessageConsumer {

    private final KafkaConsumer<String, String> consumer;

    public KafkaMessageConsumer(String topic) {

        Properties properties = this.initProperties();
        consumer = new KafkaConsumer<>(properties);
        // 订阅主题
        consumer.subscribe(Collections.singleton(topic));
        new Thread(this::consume).start();
    }

    public static void main(String[] args) {
        new KafkaMessageConsumer("willian-test-topic");
    }

    private void consume() {
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    String info = String.format("[Topic: %s][Partition:%d][Offset:%d][Key:%s][Message:%s]",
                            record.topic(), record.partition(), record.offset(), record.key(), record.value());
                    System.out.println("Received:" + info);
                }
//                consumer.commitSync();
//                consumer.commitAsync();
            }
        } finally {
            consumer.close();
        }

    }

    private Properties initProperties() {

        Properties properties = new Properties();
        // 指定 Broker
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "test");
        // 指定消费组群 ID
        properties.put("group.id", "experiment");
        // 将 key 的字节数组转成 Java 对象
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // 将 value 的字节数组转成 Java 对象
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return properties;
    }
}
