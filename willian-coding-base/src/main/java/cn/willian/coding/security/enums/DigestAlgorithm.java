package cn.willian.coding.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 摘要算法: 可用于验证数据完整性，具有防篡改检测的功能；
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-20 09:14:46
 */
@Getter
@AllArgsConstructor
public enum DigestAlgorithm {

    MD5("MD5",128,16),
    RipeMD160("RipeMD-160",160,20),
    SHA1("SHA-1",160,20),
    SHA256("SHA-256",256,32),
    SHA512("SHA-512",512,64),

    ;

    private final String code;
    /**
     * 输入长度（位）
     */
    private final Integer inputLength;
    /**
     * 输出长度（字节）
     */
    private final Integer outputLength;
}
