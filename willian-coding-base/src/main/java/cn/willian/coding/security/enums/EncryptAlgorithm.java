package cn.willian.coding.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-19 22:51:24
 */
@Getter
@AllArgsConstructor
public enum EncryptAlgorithm {

    /**
     * AES对称加密算法
     */
    AES("AES"),
    /**
     * RSA非对称加密算法
     */
    RSA("RSA");

    private final String code;
}
