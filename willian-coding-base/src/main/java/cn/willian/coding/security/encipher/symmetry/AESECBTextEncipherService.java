package cn.willian.coding.security.encipher.symmetry;

import javax.crypto.Cipher;

import cn.willian.coding.security.bean.KeyHolder;
import cn.willian.coding.security.enums.AesDecryptMode;
import cn.willian.coding.security.enums.CipherMode;
import cn.willian.coding.security.enums.EncryptAlgorithm;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-19 21:17:33
 */
public class AESECBTextEncipherService extends AbstractAESTextEncipherService {

    // 秘钥必须为16个字节
    @Override
    protected AesDecryptMode getDecryptMode() {
        return AesDecryptMode.ECB;
    }

    @Override
    protected Cipher getCipher(KeyHolder secretKey, CipherMode cipherMode, Object... ext) throws Exception {

        // 算法模式/加密模式/填充类型
        Cipher cipher = Cipher.getInstance(String.format("%s/%s/%s", EncryptAlgorithm.AES.getCode(),
            getDecryptMode().getCode(), getPaddingMode().getCode()));
        cipher.init(cipherMode.getCode(), getKey(secretKey, cipherMode));
        return cipher;
    }
}
