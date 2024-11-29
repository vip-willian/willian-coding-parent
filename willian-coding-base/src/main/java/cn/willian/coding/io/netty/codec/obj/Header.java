package cn.willian.coding.io.netty.codec.obj;

import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-21 23:49:47
 */
@Data
public class Header {

    /**
     * 4位的版本号
     */
    private int version;
    /**
     * 1位的请求类型
     */
    private byte type;
    /**
     * 内容长度
     */
    private int length;
}
