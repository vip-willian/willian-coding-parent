package cn.willian.coding.io.netty.codec;

import com.alibaba.fastjson.JSON;

import cn.willian.coding.io.netty.codec.enums.ReqType;
import cn.willian.coding.io.netty.codec.obj.MessageRecord;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-22 00:06:40
 */
public class CustomProtocolServer {

    public static void main(String[] args) {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(4);

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap().group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<NioSocketChannel>() {

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
                                System.out.println("服务端收到消息：" + JSON.toJSONString(messageRecord));
                                messageRecord.getHeader().setType(ReqType.RESPONSE.getCode());
                                messageRecord.setBody("服务器已收到消息");
                                ctx.writeAndFlush(messageRecord);
                            }
                        });
                    }
                });
            ChannelFuture channelFuture = serverBootstrap.bind(1235).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            System.out.println("绑定异常");
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
