package cn.willian.coding.io.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * 阻塞IO客户端2
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-20 20:37:17
 */
@Slf4j
public class NewBIOClient2 {

    public static void main(String[] args) {

        try (Socket socket = new Socket("127.0.0.1", 6661)) {
            System.out.println("开启一个客户端2，绑定端口为：6661");

            OutputStream outputStream = socket.getOutputStream();
            while (true) {
                // 循环不断发送数据
                String message = "客户端2发送一个数据包：" + RandomUtil.randomString(6);
                outputStream.write(message.getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            log.error("客户端异常", e);
        }
    }
}
