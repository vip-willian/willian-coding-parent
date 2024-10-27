package cn.willian.coding;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.util.internal.StringUtil;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-15 15:14:06
 */
public class TestByteBuf {

    public static void main(String[] args) {
        // 默认256
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(10);
        log(buffer);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 30; i++) {
            builder.append("a");
        }
        buffer.writeBytes(builder.toString().getBytes());
        log(buffer);
    }

    private static void log(ByteBuf buffer) {
        int length = buffer.readableBytes();
        int rows = length / 16 + (length % 15 == 0 ? 0 : 1) + 4;
        StringBuilder builder = new StringBuilder(rows * 80 * 2).append("read index:").append(buffer.readerIndex())
                .append(" write index:").append(buffer.writerIndex()).append(" capacity:").append(buffer.capacity())
                .append(StringUtil.NEWLINE);
        ByteBufUtil.appendPrettyHexDump(builder, buffer);
        System.out.println(builder);
    }
}
