package cn.willian.coding.kafka;

import com.google.common.collect.Lists;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-08 14:26:36
 */
public class KafkaMessageProducer {

    private final Producer<String, String> producer;

    public KafkaMessageProducer() {

        Properties properties = this.initProperties();
        producer = new KafkaProducer<>(properties);
    }

    public static void main(String[] args) {

        KafkaMessageProducer kafkaMessageProducer = new KafkaMessageProducer();
        kafkaMessageProducer.syncSend("hello sync send java message");
        kafkaMessageProducer.syncSend("hello sync send kafka message");

        kafkaMessageProducer.asyncSend("hello async send java message");
        kafkaMessageProducer.asyncSend("hello async send kafka message");
    }

    /**
     * 同步发送
     */
    private void syncSend(String message) {

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("willian-test-topic", message);
        try {
            RecordMetadata recordMetadata = producer.send(producerRecord).get(10, TimeUnit.SECONDS);
            long offset = recordMetadata.offset();
            System.out.println(offset);
            String topic = recordMetadata.topic();
            int partition = recordMetadata.partition();
            System.out.println(topic);
            System.out.println(partition);
        } catch (Exception e) {
            System.out.println("发送失败");
        }
    }

    /**
     * 异步发送
     */
    private void asyncSend(String message) {

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("willian-test-topic", message);
        producer.send(producerRecord, (recordMetadata, e) -> {
            if (recordMetadata != null && Objects.nonNull(e)) {
                long offset = recordMetadata.offset();
                System.out.println(offset);
                String topic = recordMetadata.topic();
                int partition = recordMetadata.partition();
                System.out.println(topic);
                System.out.println(partition);
            }
        });
    }

    /**
     * 事务消息
     */
    private void sendTransaction(String message) {

        producer.initTransactions();
        try {
            producer.beginTransaction();
            syncSend(message);
            syncSend(message);
            syncSend(message);
            producer.commitTransaction();
        } catch (Exception e) {
            producer.abortTransaction();
        }
    }

    private Properties initProperties() {

        Properties properties = new Properties();
        // 设置broker
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // 将 key 的 Java 对象转成字节数组
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        // 将 value 的 Java 对象转成字节数组
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        // 消息至少成功发给一个副本后才返回成功
        // -- ack = 0 不用等，直接返回ack
        // -- ack = 1 leader落盘成功，返回ack
        // -- ack = -1 leader&follower落盘成功，返回ack
        properties.put(ProducerConfig.ACKS_CONFIG, "1");
        // 消息重试 3 次
        properties.put(ProducerConfig.RETRIES_CONFIG, "3");
        // 启用幂等性
        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        // 事务ID
        // properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "willian-traction-id");

        List<String> interceptors = Lists.newArrayList();
        interceptors.add("cn.willian.coding.kafka.interceptor.CustomProducerInterceptor");
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);
        return properties;
    }
}
