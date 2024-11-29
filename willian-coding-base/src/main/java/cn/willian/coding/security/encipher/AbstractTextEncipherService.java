package cn.willian.coding.security.encipher;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.function.Function;

import javax.crypto.Cipher;

import cn.willian.coding.security.bean.KeyHolder;
import cn.willian.coding.security.enums.CipherMode;
import lombok.extern.slf4j.Slf4j;

/**
 * 文本安全服务
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-19 20:51:14
 */
@Slf4j
public abstract class AbstractTextEncipherService implements TextEncipherService {

    @Override
    public String encrypt(String text, KeyHolder secretKey, Object... ext) {
        return doEncryptOrDecrypt(text, secretKey, CipherMode.ENCRYPT,
            // 原文内容 普通编码方式 转换成 byte[] 字节
            data -> data.getBytes(StandardCharsets.UTF_8),
            // 加密后的结果，通过Base64进行编码
            data -> Base64.getEncoder().encodeToString(data), ext);
    }

    @Override
    public String decrypt(String text, KeyHolder secretKey, Object... ext) {
        return doEncryptOrDecrypt(text, secretKey, CipherMode.DECRYPT,
            // 解密时，先将Base64编码的内容进行解码
            data -> Base64.getDecoder().decode(data),
            // 返回对应原来的编码
            data -> new String(data, StandardCharsets.UTF_8), ext);
    }

    /**
     * 执行加密或者解密
     */
    protected String doEncryptOrDecrypt(String text, KeyHolder secretKey, CipherMode cipherMode,
        Function<String, byte[]> textToByteFuc, Function<byte[], String> byteToTextFunc, Object... ext) {

        try {
            Cipher cipher = getCipher(secretKey, cipherMode, ext);
            byte[] data = cipher.doFinal(textToByteFuc.apply(text));
            return byteToTextFunc.apply(data);
        } catch (Exception e) {
            log.error("加解密失败", e);
            throw new RuntimeException("加密解密失败");
        }
    }

    protected abstract Cipher getCipher(KeyHolder secretKey, CipherMode cipherMode, Object... ext) throws Exception;

    protected abstract Key getKey(KeyHolder secretKey, CipherMode cipherMode) throws Exception;
}
