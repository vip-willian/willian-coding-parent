package cn.willian.coding;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-15 09:36:13
 */
@Slf4j
public class TestEventLoop {
    public static void main(String[] args) {
        // 1、普通任务、定时任务、io事件
        EventLoopGroup group = new NioEventLoopGroup(2);

        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());
        // System.out.println(NettyRuntime.availableProcessors());
        // EventLoopGroup defaultEventLoopGroup = new DefaultEventLoopGroup();


        group.next().submit(()->{
            System.out.println("");
        });
    }
}
