package cn.willian.coding.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

/**
 * 阻塞IO服务端：会产生黏包半包的问题
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-20 20:37:17
 */
@Slf4j
public class NewBIOServer {

    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(6661)) {
            System.out.println("服务端启动了，监听端口为：6661");
            while (true) {
                System.out.println("等待客户端连接....");
                // 等待客户端连接，当前阻塞阻塞状态
                Socket clientSocket = serverSocket.accept();
                // 从客户端读取数据
                executor.submit(() -> handleSocket(clientSocket));
            }
        } catch (IOException e) {
            log.error("服务器异常", e);
        }
    }

    private static void handleSocket(Socket clientSocket) {

        // 读取数据
        System.out.println("线程信息id = " + Thread.currentThread().getId() + "名字 = " + Thread.currentThread().getName()
            + "客户端=" + clientSocket);
        try {
            InputStream inputStream = clientSocket.getInputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                System.out.println(new String(buffer, 0, len));
            }
        } catch (Exception e) {
            log.error("数据读取失败", e);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                log.error("关闭客户端连接失败", e);
            }
        }
    }
}
