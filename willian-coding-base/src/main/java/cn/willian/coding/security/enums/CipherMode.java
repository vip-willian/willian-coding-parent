package cn.willian.coding.security.enums;

import javax.crypto.Cipher;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-19 21:03:22
 */
@Getter
@AllArgsConstructor
public enum CipherMode {

    /**
     * 加密
     */
    ENCRYPT(Cipher.ENCRYPT_MODE),
    /**
     * 解密
     */
    DECRYPT(Cipher.DECRYPT_MODE),;

    private final Integer code;
}
