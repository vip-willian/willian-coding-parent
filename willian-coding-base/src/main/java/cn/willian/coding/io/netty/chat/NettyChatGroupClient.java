package cn.willian.coding.io.netty.chat;

import java.util.Scanner;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * 聊天室客户端
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-21 17:07:11
 */
@Slf4j
public class NettyChatGroupClient {

    /**
     * 聊天室服务器地址
     */
    private final String host;
    /**
     * 聊天室端口号
     */
    private final Integer port;

    public NettyChatGroupClient(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) {
        new NettyChatGroupClient("127.0.0.1", 8887).start();
    }

    private void start() {

        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap().group(workerGroup).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        // 添加编码器
                        pipeline.addLast(new StringEncoder());
                        // 添加解码器
                        pipeline.addLast(new StringDecoder());
                        // 添加聊天业务处理handler
                        pipeline.addLast(new ChatGroupClientHandler());
                    }
                });
            // 绑定端口
            ChannelFuture connectFuture = bootstrap.connect(host, port).sync();
            Channel channel = connectFuture.channel();
            log.info("客户端已连接 address:{}", channel.localAddress());

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String msg = scanner.nextLine();
                channel.writeAndFlush(msg + "\r\n");
            }
        } catch (InterruptedException e) {
            log.error("监听端口已经被异常打断:{}", port, e);
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
