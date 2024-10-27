package cn.willian.coding;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-15 10:16:07
 */
@Slf4j
public class NettyClientTest {

    public static void main(String[] args) {

        client1(5566);
    }

    private static void client1(int port) {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap().group(worker).channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    log.info("连接建立成功---->{}", ctx.channel());
                                    for (int i = 0; i < 10; i++) {
                                        ByteBuf buf = ctx.alloc().buffer(16);
                                        buf.writeBytes(
                                                new byte[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
                                        ctx.writeAndFlush(buf);
                                    }
                                }
                            });
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("localhost", port)).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("client error", e);
        } finally {
            worker.shutdownGracefully();
        }
    }
}
