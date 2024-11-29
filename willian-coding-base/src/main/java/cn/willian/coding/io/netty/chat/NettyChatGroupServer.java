package cn.willian.coding.io.netty.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * 聊天室服务
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-21 17:07:11
 */
@Slf4j
public class NettyChatGroupServer {

    /**
     * 聊天室端口号
     */
    private final Integer port;

    public NettyChatGroupServer(Integer port) {
        this.port = port;
    }

    public static void main(String[] args) {
        new NettyChatGroupServer(8887).start();
    }

    private void start() {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(4);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap().group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        // 添加编码器
                        pipeline.addLast(new StringEncoder());
                        // 添加解码器
                        pipeline.addLast(new StringDecoder());
                        // 添加聊天业务处理handler
                        pipeline.addLast(new ChatGroupServerHandler());
                    }
                });
            // 绑定端口
            ChannelFuture bindFuture = serverBootstrap.bind(port).sync();
            log.info("聊天服务器已经启动 port:{}", port);
            // 监听通道关闭
            bindFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("监听端口已经被异常打断:{}", port, e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
