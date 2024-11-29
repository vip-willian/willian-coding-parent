package cn.willian.coding.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-19 22:48:35
 */
@Getter
@AllArgsConstructor
public enum PaddingMode {

    PKCS5Padding("PKCS5Padding"),;

    private final String code;
}
