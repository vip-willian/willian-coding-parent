package cn.willian.coding.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * AES支持的加密模式
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-19 22:46:30
 */
@Getter
@AllArgsConstructor
public enum AesDecryptMode {

    /**
     * 用相同的密钥分别对明文分组独立加密
     */
    ECB("ECB", "电码本模式 Electronic Codebook Book"),
    /**
     * 加密算法的输入是上一个密文组合下一个明文组的异或，引入初始向量
     */
    CBC("CBC", "密码分组链接模式 Cipher Block Chaining"),
    /**
     * 一次处理s位，上一块密文作为加密算法的输入，产生的伪随机数输出与明文异或作为下一个单元的密文
     */
    CFB("CFB", "密码反馈模式 Cipher FeedBack"),
    /**
     * 与CFB类似，只是加密算法的输入是上一次加密的输出，且使用整个分组
     */
    OFB("OFB", "输出反馈模式 Output FeedBack"),
    /**
     * 每个明文分组都与一个经过加密的计数器相异或。对每个后续分组计数器递增
     */
    CTR("CTR", "计算器模式 Counter"),
    /**
     * GCM CTR + 并且带有GMAC消息认证码
     * GMAC消息认证码的作用就是为了验证数据的完整性。
     */
    GCM("GCM", "Galois/Counter Mode"),

    ;

    private final String code;
    private final String desc;
}
