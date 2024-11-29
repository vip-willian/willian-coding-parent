package cn.willian.coding.io.netty.codec;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;

import cn.willian.coding.io.netty.codec.obj.Header;
import cn.willian.coding.io.netty.codec.obj.MessageRecord;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-21 23:46:49
 */
public class MessageRecordDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list)
        throws Exception {

        MessageRecord messageRecord = new MessageRecord();
        Header header = new Header();
        header.setVersion(byteBuf.readInt());
        header.setType(byteBuf.readByte());
        header.setLength(byteBuf.readInt());

        messageRecord.setHeader(header);
        if (header.getLength() > 0) {
            // 解析消息体
            byte[] bytes = new byte[header.getLength()];
            byteBuf.readBytes(bytes);

            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            Object body = new ObjectInputStream(bis).readObject();
            messageRecord.setBody(body);
        }
        list.add(messageRecord);
    }
}
