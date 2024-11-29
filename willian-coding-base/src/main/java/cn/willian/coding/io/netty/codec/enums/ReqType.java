package cn.willian.coding.io.netty.codec.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-21 23:52:51
 */
@Getter
@AllArgsConstructor
public enum ReqType {

    REQUEST((byte)1), RESPONSE((byte)2), PING((byte)3), PONG((byte)4),;

    private final byte code;
}
