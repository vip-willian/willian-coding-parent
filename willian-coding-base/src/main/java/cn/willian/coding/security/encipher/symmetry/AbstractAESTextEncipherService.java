package cn.willian.coding.security.encipher.symmetry;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Objects;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.csp.sentinel.util.StringUtil;

import cn.willian.coding.security.bean.KeyHolder;
import cn.willian.coding.security.encipher.AbstractTextEncipherService;
import cn.willian.coding.security.enums.AesDecryptMode;
import cn.willian.coding.security.enums.CipherMode;
import cn.willian.coding.security.enums.EncryptAlgorithm;
import cn.willian.coding.security.enums.PaddingMode;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-19 21:12:46
 */
public abstract class AbstractAESTextEncipherService extends AbstractTextEncipherService {

    @Override
    public KeyHolder getKey() {

        try {
            // 获取随机的一个秘钥KEY
            KeyGenerator kg = KeyGenerator.getInstance(EncryptAlgorithm.AES.getCode());
            // 支持128、192、256 三种不同密码长度，不同密码长度安全性也不一样
            kg.init(256, new SecureRandom());
            SecretKey secretKey = kg.generateKey();
            return KeyHolder.ofSymmetry(secretKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有此算法");
        }
    }

    @Override
    protected Key getKey(KeyHolder secretKey, CipherMode cipherMode) {

        if (StringUtil.isNotBlank(secretKey.getPassword())) {
            return new SecretKeySpec(secretKey.getPassword().getBytes(StandardCharsets.UTF_8),
                EncryptAlgorithm.AES.getCode());
        }
        if (Objects.nonNull(secretKey.getKey())) {
            return secretKey.getKey();
        }
        throw new RuntimeException("秘钥KEY不存在，请检查请求参数");
    }

    /**
     * 获取加密模式
     */
    protected abstract AesDecryptMode getDecryptMode();

    /**
     * 获取填充模式
     */
    protected PaddingMode getPaddingMode() {

        return PaddingMode.PKCS5Padding;
    }
}
