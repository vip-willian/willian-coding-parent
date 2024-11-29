package cn.willian.coding.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-20 21:10:54
 */
@Slf4j
public class NewNIOBlockingServer {

    private static final ExecutorService executor = Executors.newCachedThreadPool();
    private static final ByteBuffer READ_BUFFER = ByteBuffer.allocate(1024);
    private static final ByteBuffer WRITE_BUFFER = ByteBuffer.allocate(1024);

    public static void main(String[] args) {
        // 创建套接字
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            // 绑定6662端口
            serverSocketChannel.bind(new InetSocketAddress(6662));
            // 默认为阻塞模式
            while (true) {
                System.out.println("等待客户端连接");
                SocketChannel socketChannel = serverSocketChannel.accept();
                executor.submit(() -> handleSocket(socketChannel));
            }
        } catch (IOException e) {
            log.error("服务器异常", e);
        }
    }

    private static void handleSocket(SocketChannel socketChannel) {

        try {
            int len = socketChannel.read(READ_BUFFER);
            while (len != -1) {
                // 写入完buffer后
                // 转换成读模式进行读取
                READ_BUFFER.flip();
                System.out.println(StandardCharsets.UTF_8.decode(READ_BUFFER));
                READ_BUFFER.clear();
                len = socketChannel.read(READ_BUFFER);
            }
        } catch (Exception e) {
            log.error("数据读取失败", e);
        } finally {
            try {
                socketChannel.close();
            } catch (IOException e) {
                log.error("关闭客户端连接失败", e);
            }
        }
    }
}
