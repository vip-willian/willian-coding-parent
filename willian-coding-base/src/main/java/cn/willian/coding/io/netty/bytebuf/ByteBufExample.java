package cn.willian.coding.io.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-21 22:32:27
 */
public class ByteBufExample {
    public static void main(String[] args) {

        // 使用JVM内存
        ByteBufAllocator.DEFAULT.heapBuffer();
        // 使用堆外内存
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.directBuffer();
    }
}
