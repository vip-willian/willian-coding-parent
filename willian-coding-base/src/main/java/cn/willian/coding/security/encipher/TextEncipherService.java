package cn.willian.coding.security.encipher;

import cn.willian.coding.security.bean.KeyHolder;

/**
 * 文本安全服务
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-19 20:51:14
 */
public interface TextEncipherService {

    /**
     * 获取秘钥KEY信息
     * 
     * @return 秘钥KEY信息
     */
    KeyHolder getKey();

    /**
     * 加密
     * 
     * @param text 待加密文本内容
     * @param secretKey 秘钥信息
     * @return 加密内容
     */
    String encrypt(String text, KeyHolder secretKey, Object... ext);

    /**
     * 解密
     *
     * @param text 待解密文本内容
     * @param secretKey 秘钥信息
     * @return 明文内容
     */
    String decrypt(String text, KeyHolder secretKey, Object... ext);
}
