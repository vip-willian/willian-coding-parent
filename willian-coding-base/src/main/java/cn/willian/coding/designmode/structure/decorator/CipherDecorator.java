package cn.willian.coding.designmode.structure.decorator;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 11:12:02
 */
// Decorator 抽象装饰类
public abstract class CipherDecorator implements Cipher{

    private final Cipher cipher;

    public CipherDecorator(Cipher cipher) {
        this.cipher = cipher;
    }

    @Override
    public String encrypt(String content) {
        return cipher.encrypt(content);
    }
}
