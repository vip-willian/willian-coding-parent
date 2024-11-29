package cn.willian.coding.io.netty.heartbeat;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Throwables;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-21 20:59:24
 */
@Slf4j
public class NettyHeathBeatServer {
    public static void main(String[] args) {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(4);

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap().group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        // 加入一个netty 提供 IdleStateHandler
                        /**
                         * 说明 1. IdleStateHandler 是netty 提供的处理空闲状态的处理器 2. long readerIdleTime : 表示多长时间没有读,
                         * 就会发送一个心跳检测包检测是否连接 3. long writerIdleTime : 表示多长时间没有写, 就会发送一个心跳检测包检测是否连接 4. long allIdleTime :
                         * 表示多长时间没有读写, 就会发送一个心跳检测包检测是否连接
                         * 
                         * 5. 文档说明 triggers an {@link IdleStateEvent } when a {@link Channel} has not performed read,
                         * write, or both operation for a while. 6. 当 IdleStateEvent 触发后 , 就会传递给管道 的下一个handler去处理
                         * 通过调用(触发)下一个handler 的 userEventTiggered , 在该方法中去处理 IdleStateEvent(读空闲，写空闲，读写空闲)
                         */
                        pipeline.addLast(new IdleStateHandler(7000, 7000, 10, TimeUnit.SECONDS));
                        pipeline.addLast(new MyServerHandler());
                    }
                });
            ChannelFuture channelFuture = serverBootstrap.bind(1122).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.warn("端口绑定被打断:{}", Throwables.getStackTraceAsString(e));
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
