package cn.willian.coding.security.encipher;

import java.security.SecureRandom;

import cn.willian.coding.security.bean.KeyHolder;
import cn.willian.coding.security.encipher.dissymmetry.RSATextEncipherService;
import cn.willian.coding.security.encipher.symmetry.AESCBCTextEncipherService;
import cn.willian.coding.security.encipher.symmetry.AESECBTextEncipherService;
import cn.willian.coding.security.encipher.symmetry.AESGCMTextEncipherService;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-19 21:22:10
 */
@SuppressWarnings("all")
public class TextEncipherTest {

    public static String ENCRYPT_KEY = "Y5MUIOM7BUWI7BQR";
    public static String IV_PARAMETER = "HUYNY887R632KIWN";

    public static void main(String[] args) {

        String text = "中华人民共和国";

        System.out.println("-------- AES-ECB 模式 --------");
        testAesEcb(text);

        System.out.println("-------- AES-CBC 模式 --------");
        testAesCbc(text);

        System.out.println("-------- AES-GCM 模式 --------");
        testAesGcm(text);

        System.out.println("-------- RSA 模式 --------");
        testRsa(text);
    }

    private static void testAesEcb(String text) {

        TextEncipherService service = new AESECBTextEncipherService();
        // KeyHolder key = KeyHolder.ofSymmetry(ENCRYPT_KEY);
        KeyHolder key = service.getKey();

        System.out.println("秘钥信息:" + key.getKeyString());

        long startTime = System.currentTimeMillis();
        String encryptContent = service.encrypt(text, key);
        System.out.println("AES-ECB加密耗时：" + (System.currentTimeMillis() - startTime) + "ms, 加密后的内容:" + encryptContent);

        startTime = System.currentTimeMillis();
        String decryptContent = service.decrypt(encryptContent, key);
        System.out.println("AES-ECB解密耗时：" + (System.currentTimeMillis() - startTime) + "ms, 解密后的内容:" + decryptContent);
    }

    private static void testAesCbc(String text) {

        TextEncipherService service = new AESCBCTextEncipherService();
        // KeyHolder key = KeyHolder.ofSymmetry(ENCRYPT_KEY);
        KeyHolder key = service.getKey();

        System.out.println("秘钥信息:" + key.getKeyString());

        long startTime = System.currentTimeMillis();
        String encryptContent = service.encrypt(text, key, IV_PARAMETER);
        System.out.println("AES-CBC加密耗时：" + (System.currentTimeMillis() - startTime) + "ms, 加密后的内容:" + encryptContent);

        startTime = System.currentTimeMillis();
        String decryptContent = service.decrypt(encryptContent, key, IV_PARAMETER);
        System.out.println("AES-CBC解密耗时：" + (System.currentTimeMillis() - startTime) + "ms, 解密后的内容:" + decryptContent);
    }

    private static void testAesGcm(String text) {

        byte[] IV = new byte[12];
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);

        TextEncipherService service = new AESGCMTextEncipherService();
        // KeyHolder key = KeyHolder.ofSymmetry(ENCRYPT_KEY);
        KeyHolder key = service.getKey();

        System.out.println("秘钥信息:" + key.getKeyString());

        long startTime = System.currentTimeMillis();
        String encryptContent = service.encrypt(text, key, IV);
        System.out.println("AES-GCM加密耗时：" + (System.currentTimeMillis() - startTime) + "ms, 加密后的内容:" + encryptContent);

        startTime = System.currentTimeMillis();
        String decryptContent = service.decrypt(encryptContent, key, IV);
        System.out.println("AES-GCM解密耗时：" + (System.currentTimeMillis() - startTime) + "ms, 解密后的内容:" + decryptContent);
    }

    private static void testRsa(String text) {

        TextEncipherService service = new RSATextEncipherService();
        KeyHolder key = service.getKey();

        System.out.println("公钥信息:" + key.getPublicKeyString());
        System.out.println("私钥信息:" + key.getPrivateKeyString());

        long startTime = System.currentTimeMillis();
        String encryptContent = service.encrypt(text, key);
        System.out.println("RSA公钥加密耗时：" + (System.currentTimeMillis() - startTime) + "ms, 加密后的内容:" + encryptContent);

        startTime = System.currentTimeMillis();
        String decryptContent = service.decrypt(encryptContent, key);
        System.out.println("RSA私钥解密耗时：" + (System.currentTimeMillis() - startTime) + "ms, 解密后的内容:" + decryptContent);
    }
}
