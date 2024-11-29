package cn.willian.coding.security.encipher.dissymmetry;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;

import cn.willian.coding.security.bean.KeyHolder;
import cn.willian.coding.security.encipher.AbstractTextEncipherService;
import cn.willian.coding.security.enums.CipherMode;
import cn.willian.coding.security.enums.EncryptAlgorithm;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-20 00:14:19
 */
public class RSATextEncipherService extends AbstractTextEncipherService {

    @Override
    protected Cipher getCipher(KeyHolder secretKey, CipherMode cipherMode, Object... ext) throws Exception {

        Cipher cipher = Cipher.getInstance(EncryptAlgorithm.RSA.getCode());
        cipher.init(cipherMode.getCode(), getKey(secretKey, cipherMode));
        return cipher;
    }

    @Override
    protected Key getKey(KeyHolder secretKey, CipherMode cipherMode) {

        // 公钥进行加密
        if (CipherMode.ENCRYPT.equals(cipherMode)) {
            return secretKey.getPublicKey();
        }
        // 私钥进行加密
        if (CipherMode.DECRYPT.equals(cipherMode)) {
            return secretKey.getPrivateKey();
        }
        throw new RuntimeException("秘钥KEY不存在，请检查请求参数");
    }

    @Override
    public KeyHolder getKey() {

        // 生成 公钥/ 私钥对
        KeyPairGenerator generator;
        try {
            generator = KeyPairGenerator.getInstance(EncryptAlgorithm.RSA.getCode());
            generator.initialize(1024);
            KeyPair keyPair = generator.generateKeyPair();
            return KeyHolder.ofDisSymmetry(keyPair.getPublic(), keyPair.getPrivate());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
