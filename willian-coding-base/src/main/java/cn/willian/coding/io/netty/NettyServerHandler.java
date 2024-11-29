package cn.willian.coding.io.netty;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 *
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-21 16:48:56
 */
@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    // 接收客户端的数据
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        Channel channel = ctx.channel();
        // 读取数据
        ByteBuf byteBuf = (ByteBuf)msg;
        log.info("客户端【{}】说：{}", channel.remoteAddress(), byteBuf.toString(Charset.defaultCharset()));
    }

    // 客户端读取完成
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        channel.writeAndFlush(Unpooled.copiedBuffer("hello client".getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        log.error(cause.getMessage(), cause);
        ctx.close();
    }
}
