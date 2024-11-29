package cn.willian.coding.io.netty.codec;

import com.alibaba.fastjson2.JSON;

import cn.willian.coding.io.netty.codec.enums.ReqType;
import cn.willian.coding.io.netty.codec.obj.Header;
import cn.willian.coding.io.netty.codec.obj.MessageRecord;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-22 00:06:40
 */
public class CustomProtocolClient {

    public static void main(String[] args) {

        NioEventLoopGroup workerGroup = new NioEventLoopGroup(1);
        try {
            Bootstrap bootstrap = new Bootstrap().group(workerGroup).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new LengthFieldBasedFrameDecoder(1024 * 1024, 5, 4, 0, 0));
                        pipeline.addLast(new MessageRecordEncoder());
                        pipeline.addLast(new MessageRecordDecoder());
                        pipeline.addLast(new ChannelInboundHandlerAdapter() {

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                MessageRecord messageRecord = (MessageRecord)msg;
                                System.out.println("客户端收到回应：" + JSON.toJSONString(messageRecord));
                            }
                        });
                    }
                });
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 1235).sync();
            Channel channel = channelFuture.channel();
            for (int i = 0; i < 20; i++) {

                String message = "这是第" + (i + 1) + "条消息";
                MessageRecord messageRecord = new MessageRecord();
                Header header = new Header();
                header.setVersion(i);
                header.setType(ReqType.REQUEST.getCode());
                header.setLength(message.length());
                messageRecord.setHeader(header);
                messageRecord.setBody(message);

                channel.writeAndFlush(messageRecord);
            }
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            System.out.println("绑定异常");
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
