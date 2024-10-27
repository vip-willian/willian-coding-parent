package cn.willian.coding;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import com.google.common.base.Charsets;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-14 20:49:54
 */
@Slf4j
public class TestServer {

    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();

        // 1、创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        // 2、绑定端口
        ssc.bind(new InetSocketAddress(1234));

        ssc.register(selector, SelectionKey.OP_ACCEPT, null);

        // 3、与客户端建立连接
        while (true) {
            // 没有事件发生，阻塞， 有事件，执行
            selector.select();
            // 处理事件,内部包含了所有发生的事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    ByteBuffer byteBuffer = ByteBuffer.allocate(10);
                    socketChannel.register(selector, SelectionKey.OP_READ, byteBuffer);
                    log.info("建立客户端连接===> {}", socketChannel);
                } else if (key.isReadable()) {
                    SocketChannel socketChannel = null;
                    try {
                        socketChannel = (SocketChannel) key.channel();
                        // 将 channel的内容读到buffer
                        // 如果是正常断开，read结果返回-1
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        int len = socketChannel.read(buffer);
                        if (len == -1) {
                            log.info("客户端正常断开===> {}", socketChannel);
                            key.cancel();
                        } else {
                            split(buffer);
                            if(buffer.position() == buffer.limit()){
                                ByteBuffer newBuffer = ByteBuffer.allocate(buffer.capacity() * 2);
                                buffer.flip();
                                newBuffer.put(buffer);
                                key.attach(newBuffer);
                            }
                            // buffer.flip();
                            // while (buffer.hasRemaining()) {
                            // byte b = buffer.get();
                            // log.info("{}", (char) b);
                            // }
                            // buffer.clear();
                        }
                    } catch (Exception e) {
                        // 客户端断开连接需要删除
                        log.info("客户端异常断开===> {}", socketChannel);
                        key.cancel();
                    }
                }
                // 每次处理完事件，需要移除某个事件
                iterator.remove();
            }
        }
    }

    private static void split(ByteBuffer source) {
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            if (source.get(i) == '\n') {
                int length = i + 1 - source.position();
                ByteBuffer target = ByteBuffer.allocate(length);
                for (int j = 0; j < length; j++) {
                    target.put(source.get());
                }
                log.info("{}", Charsets.UTF_8.decode(target));
            }
        }
        source.compact();
    }
}
