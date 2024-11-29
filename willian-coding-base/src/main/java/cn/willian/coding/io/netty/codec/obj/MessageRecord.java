package cn.willian.coding.io.netty.codec.obj;

import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-21 23:49:00
 */
@Data
public class MessageRecord {

    private Header header;
    private Object body;
}
