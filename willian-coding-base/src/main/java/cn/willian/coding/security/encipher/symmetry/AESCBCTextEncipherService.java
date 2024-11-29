package cn.willian.coding.security.encipher.symmetry;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.lang3.ArrayUtils;

import cn.willian.coding.security.bean.KeyHolder;
import cn.willian.coding.security.enums.AesDecryptMode;
import cn.willian.coding.security.enums.CipherMode;
import cn.willian.coding.security.enums.EncryptAlgorithm;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-19 21:17:33
 */
public class AESCBCTextEncipherService extends AbstractAESTextEncipherService {

    // 秘钥必须为16个字节
    @Override
    protected AesDecryptMode getDecryptMode() {
        return AesDecryptMode.CBC;
    }

    @Override
    protected Cipher getCipher(KeyHolder secretKey, CipherMode cipherMode, Object... ext) throws Exception {

        // 算法模式/加密模式/填充类型
        Cipher cipher = Cipher.getInstance(String.format("%s/%s/%s", EncryptAlgorithm.AES.getCode(),
            getDecryptMode().getCode(), getPaddingMode().getCode()));
        // 增加一个向量数据
        String ivParameter = getIvParameter(ext);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivParameter.getBytes(StandardCharsets.UTF_8));
        cipher.init(cipherMode.getCode(), getKey(secretKey, cipherMode), ivParameterSpec);
        return cipher;
    }

    private String getIvParameter(Object[] ext) {

        if (ArrayUtils.isEmpty(ext)) {
            throw new RuntimeException("缺少初始向量参数");
        }
        Object obj = ext[0];
        if (obj instanceof String) {
            String ivParameter = (String)obj;
            if (ivParameter.length() != 16) {
                throw new RuntimeException("向量参数长度不等于16");
            }
            return ivParameter;
        }
        throw new RuntimeException("缺少初始向量参数");
    }
}
