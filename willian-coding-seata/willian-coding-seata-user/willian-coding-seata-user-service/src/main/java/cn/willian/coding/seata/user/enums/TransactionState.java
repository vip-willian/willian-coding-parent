package cn.willian.coding.seata.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-12 00:01:57
 */
@Getter
@AllArgsConstructor
public enum TransactionState {

    TRY(0, "try"),
    CONFIRM(1, "confirm"),
    CANCEL(2, "cancel"),

    ;

    private final Integer code;
    private final String name;
}
