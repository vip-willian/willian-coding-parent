package cn.willian.coding.security.encipher.symmetry;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;

import cn.willian.coding.security.bean.KeyHolder;
import cn.willian.coding.security.enums.AesDecryptMode;
import cn.willian.coding.security.enums.CipherMode;
import cn.willian.coding.security.enums.EncryptAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-19 21:17:33
 */
@Slf4j
public class AESGCMTextEncipherService extends AbstractAESTextEncipherService {

    @Override
    protected AesDecryptMode getDecryptMode() {
        return AesDecryptMode.GCM;
    }

    @Override
    protected Cipher getCipher(KeyHolder secretKey, CipherMode cipherMode, Object... ext) throws Exception {

        // 算法模式/加密模式/填充类型
        Cipher cipher = Cipher.getInstance(String.format("%s/%s/%s", EncryptAlgorithm.AES.getCode(),
            getDecryptMode().getCode(), getPaddingMode().getCode()));

        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, (byte[])ext[0]);
        cipher.init(cipherMode.getCode(), getKey(secretKey, cipherMode), gcmParameterSpec);

        // 支持添加关联数据
        // cipher.updateAAD();
        return cipher;
    }
}
