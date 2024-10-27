package cn.willian.coding;

import com.google.common.base.Charsets;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-14 21:16:03
 */
public class TestClient {
    public static void main(String[] args) throws Exception {

        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("127.0.0.1", 1234));
//        sc.write(Charset.defaultCharset().encode("我是中国人\n我爱我的祖国\n"));
        sc.write(Charsets.UTF_8.encode("323\n132\n"));
        System.in.read();
    }
}
