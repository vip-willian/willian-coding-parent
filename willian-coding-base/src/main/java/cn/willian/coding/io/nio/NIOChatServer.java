package cn.willian.coding.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import lombok.extern.slf4j.Slf4j;

/**
 * 聊天室服务端
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-20 23:58:35
 */
@Slf4j
public class NIOChatServer {

    private static final ByteBuffer READ_BUFFER = ByteBuffer.allocate(1024);
    private static final ByteBuffer WRITE_BUFFER = ByteBuffer.allocate(1024);
    private final int port;

    public NIOChatServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        new NIOChatServer(8888).start();
    }

    public void start() {
        // 创建服务端ServerSocketChannel套接字
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 开启一个多路复用器
            Selector selector = Selector.open()) {
            // 设置为非阻塞模式
            serverSocketChannel.configureBlocking(false);
            // 设置端口绑定
            serverSocketChannel.bind(new InetSocketAddress(port));
            // 注册可接受客户端的连接事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            log.info("服务端已经启动，监听端口：{}", port);

            // 阻塞等待事件
            while (selector.select() > 0) {
                Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();
                while (selectionKeyIterator.hasNext()) {
                    SelectionKey selectionKey = selectionKeyIterator.next();

                    doServer(selectionKey);

                    selectionKeyIterator.remove();
                }
            }
        } catch (IOException e) {
            log.error("聊天室服务器出现异常", e);
        }
    }

    private void doServer(SelectionKey selectionKey) throws IOException {

        if (selectionKey.isAcceptable()) {
            doAccept(selectionKey);
        } else if (selectionKey.isReadable()) {
            doRead(selectionKey);
        }
    }

    // 有客户端连接进来
    private void doAccept(SelectionKey selectionKey) throws IOException {

        ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectionKey.channel();
        // 获取连接上的客户端
        SocketChannel client = serverSocketChannel.accept();
        client.configureBlocking(false);
        // 注册客户端的可读事件到多路复用器
        client.register(selectionKey.selector(), SelectionKey.OP_READ);
        log.info("客户端【{}】上线啦！", client.socket().getPort());
    }

    // 读取某个客户端发送的消息并转发到其他客户端
    private void doRead(SelectionKey selectionKey) throws IOException {

        SocketChannel client = (SocketChannel)selectionKey.channel();
        Selector selector = selectionKey.selector();
        try {
            // 获取接收到的消息
            String message = receiveMessage(client);
            log.info("客户端【\"{}\"】说：{}", client.socket().getPort(), message);

            sendMessage(client, selector, message);

            if (message.equals("quit")) {
                client.close();
                selectionKey.cancel();
                selector.wakeup();
                log.info("客户端【\"{}\"】下线了", client.socket().getPort());
            }
        } catch (IOException e) {
            // 客户端异常下线
            client.close();
            selectionKey.cancel();
        }
    }

    private void sendMessage(SocketChannel client, Selector selector, String message) {

        String msg = "客户端[" + client.socket().getPort() + "]:" + message;

        for (SelectionKey key : selector.keys()) {
            if (!(key.channel() instanceof ServerSocketChannel) && !client.equals(key.channel()) && key.isValid()) {
                SocketChannel otherClient = (SocketChannel)key.channel();
                WRITE_BUFFER.clear();
                WRITE_BUFFER.put(msg.getBytes(StandardCharsets.UTF_8));
                WRITE_BUFFER.flip();
                // 把消息写入到缓冲区后，再把缓冲区的内容写到客户端对应的通道中
                while (WRITE_BUFFER.hasRemaining()) {
                    try {
                        otherClient.write(WRITE_BUFFER);
                    } catch (IOException e) {
                        log.error("其他客户端出现异常", e);
                    }
                }
            }
        }
    }

    private String receiveMessage(SocketChannel client) throws IOException {

        // 使用读BUFFER之前先清空，避免残留数据
        READ_BUFFER.clear();

        StringBuilder builder = new StringBuilder();
        int len = client.read(READ_BUFFER);
        while (len > 0) {
            READ_BUFFER.flip();
            builder.append(StandardCharsets.UTF_8.decode(READ_BUFFER));
            READ_BUFFER.clear();
            len = client.read(READ_BUFFER);
        }
        return builder.toString();
    }
}
