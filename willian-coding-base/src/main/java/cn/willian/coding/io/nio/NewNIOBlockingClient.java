package cn.willian.coding.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-20 21:10:54
 */
@Slf4j
public class NewNIOBlockingClient {
    private static final ByteBuffer WRITE_BUFFER = ByteBuffer.allocate(1024);

    public static void main(String[] args) {
        // 创建套接字
        try (SocketChannel socketChannel = SocketChannel.open()) {
            // 绑定6662端口
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 6662));

            while (true) {
                ThreadUtil.sleep(1000);
                // 循环不断发送数据
                String message = "客户端1发送一个数据包：" + RandomUtil.randomString(6);
                WRITE_BUFFER.put(message.getBytes(StandardCharsets.UTF_8));
                WRITE_BUFFER.flip();
                socketChannel.write(WRITE_BUFFER);
                WRITE_BUFFER.clear();
            }
        } catch (IOException e) {
            log.error("客户端异常", e);
        }
    }
}
