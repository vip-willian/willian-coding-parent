package cn.willian.coding.kafka.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-08 15:07:18
 */
public class CustomProducerInterceptor implements ProducerInterceptor<String, String> {

    /**
     * 发送消息的时候触发
     */
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        System.out.println("扣一毛");
        return record;
    }

    /**
     * 收到服务端ACK后触发
     */
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        System.out.println("收到服务端响应了");
    }

    @Override
    public void close() {
        System.out.println("生产者关闭了");
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
