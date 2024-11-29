package cn.willian.coding.io.netty.codec;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

import cn.willian.coding.io.netty.codec.obj.Header;
import cn.willian.coding.io.netty.codec.obj.MessageRecord;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-21 23:48:12
 */
public class MessageRecordEncoder extends MessageToByteEncoder<MessageRecord> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageRecord messageRecord, ByteBuf byteBuf)
        throws Exception {

        // 将对象编码成二进制流
        Header header = messageRecord.getHeader();
        byteBuf.writeInt(header.getVersion());
        byteBuf.writeByte(header.getType());

        Object body = messageRecord.getBody();
        if (Objects.nonNull(body)) {
            // 将消息体编码成二进制流
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(bos);
            outputStream.writeObject(body);

            byte[] bytes = bos.toByteArray();
            byteBuf.writeInt(bytes.length);
            byteBuf.writeBytes(bytes);
        } else {
            byteBuf.writeInt(0);
        }
    }
}
