package cn.willian.coding.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;

/**
 * 聊天室客户端
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-20 23:58:35
 */
@Slf4j
public class NIOChatClient {

    private static final ByteBuffer READ_BUFFER = ByteBuffer.allocate(1024);
    private static final ByteBuffer WRITE_BUFFER = ByteBuffer.allocate(1024);
    private final int port;
    private Selector selector;
    private SocketChannel client;

    public NIOChatClient(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        new NIOChatClient(8888).start();
    }

    public void start() {
        try {
            // 创建客户端SocketChannel套接字
            client = SocketChannel.open();
            // 开启一个多路复用器
            selector = Selector.open();
            // 设置为非阻塞模式
            client.configureBlocking(false);
            // 请求服务器的连接
            client.connect(new InetSocketAddress("localhost", port));
            // 注册连接事件
            client.register(selector, SelectionKey.OP_CONNECT);

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
        } catch (ClosedSelectorException e) {
            // ignore
        }
    }

    private void doServer(SelectionKey selectionKey) throws IOException {

        if (selectionKey.isConnectable()) {
            doConnect(selectionKey);
        } else if (selectionKey.isReadable()) {
            doRead(selectionKey);
        }
    }

    // 客户端已经收到连接建立
    private void doConnect(SelectionKey selectionKey) throws IOException {

        SocketChannel client = (SocketChannel)selectionKey.channel();
        // 当连接建立成功，开启一个新线程处理用户输入
        if (client.finishConnect()) {
            new Thread(() -> handleUserInput(this)).start();
        }
        client.configureBlocking(false);
        // 注册客户端的可读事件到多路复用器
        client.register(selectionKey.selector(), SelectionKey.OP_READ);
    }

    private void doRead(SelectionKey selectionKey) throws IOException {

        SocketChannel client = (SocketChannel)selectionKey.channel();
        Selector selector = selectionKey.selector();

        // 获取接收到的消息
        String message = receiveMessage(client);
        log.info("{}", message);
        if (message.equals("quit")) {
            selectionKey.cancel();
            selector.wakeup();
        }
    }

    public void sendMessage(String message) throws IOException {

        if (!message.isEmpty()) {
            WRITE_BUFFER.clear();
            WRITE_BUFFER.put(message.getBytes(StandardCharsets.UTF_8));
            WRITE_BUFFER.flip();
            while (WRITE_BUFFER.hasRemaining()) {
                client.write(WRITE_BUFFER);
            }
            if (message.equals("quit")) {
                selector.close();
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

    private void handleUserInput(NIOChatClient chatClient) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String message = scanner.nextLine();
            try {
                chatClient.sendMessage(message);
                if (message.equals("quit")) {
                    break;
                }
            } catch (IOException e) {
                log.error("发送出现异常", e);
            }
        }
    }
}
