package cn.willian.coding.designmode.structure.decorator;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 11:09:11
 */
public interface Cipher {

    /**
     * 加密
     *
     * @param content 待加密的内容
     * @return 加密后的密文
     */
     String encrypt(String content);
}
