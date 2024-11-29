package cn.willian.coding.security.bean;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.apache.commons.lang3.StringUtils;

import cn.hutool.core.codec.Base64Encoder;
import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-19 20:52:49
 */
@Data
public class KeyHolder {

    /**
     * 对称加密KEY
     */
    private Key key;
    /**
     * 非对称加密公钥
     */
    private PublicKey publicKey;
    /**
     * 非对称加密私钥
     */
    private PrivateKey privateKey;
    /**
     * 指定固定密码
     */
    private String password;

    /**
     * 非对称加密
     * 
     * @param publicKey 公钥
     * @param privateKey 私钥
     * @return 秘钥信息
     */
    public static KeyHolder ofDisSymmetry(PublicKey publicKey, PrivateKey privateKey) {

        KeyHolder keyHolder = new KeyHolder();
        keyHolder.setPublicKey(publicKey);
        keyHolder.setPrivateKey(privateKey);
        return keyHolder;
    }

    /**
     * 对称加密 (密码长度必须为16位)
     *
     * @param password 秘钥
     * @return 秘钥信息
     */
    public static KeyHolder ofSymmetry(String password) {

        if (password.length() != 16) {
            throw new RuntimeException("密码长度必须为16位");
        }
        KeyHolder keyHolder = new KeyHolder();
        keyHolder.setPassword(password);
        return keyHolder;
    }

    /**
     * 对称加密
     *
     * @param key 秘钥
     * @return 秘钥信息
     */
    public static KeyHolder ofSymmetry(Key key) {

        KeyHolder keyHolder = new KeyHolder();
        keyHolder.setKey(key);
        return keyHolder;
    }

    public String getKeyString() {

        if (StringUtils.isNotBlank(password)) {
            return password;
        }
        return Base64Encoder.encode(key.getEncoded());
    }

    public String getPublicKeyString() {
        return Base64Encoder.encode(publicKey.getEncoded());
    }

    public String getPrivateKeyString() {
        return Base64Encoder.encode(privateKey.getEncoded());
    }
}
