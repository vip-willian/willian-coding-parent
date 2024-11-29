package cn.willian.coding.io.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-21 20:29:07
 */
public class ChatGroupServerHandler extends SimpleChannelInboundHandler<String> {

    private static final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    // 连接建立后，第一个被执行
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        // 该方法会像所有的channel遍历，并发送消息
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "加入了群聊, 当前在线人数：" + channelGroup.size());
        channelGroup.add(channel);
    }

    // 断开连接，将xx客户端下线的消息进行推送
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "离开了群聊, 当前在线人数：" + channelGroup.size());
        super.handlerRemoved(ctx);
    }

    // 处于活动状态，表示上线了
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println(ctx.channel().remoteAddress() + "上线了~");
    }

    // 处于不活动状态，表示离线了
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        System.out.println(ctx.channel().remoteAddress() + "离线了~");
    }

    // 处理接收到的读事件
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (ch == channel) {
                ch.writeAndFlush("[自己]发送了消息：" + msg + "\n");
            } else {
                ch.writeAndFlush("[客户端]" + channel.remoteAddress() + "发送了消息：" + msg + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        // 关闭通道
        ctx.close();
    }
}
