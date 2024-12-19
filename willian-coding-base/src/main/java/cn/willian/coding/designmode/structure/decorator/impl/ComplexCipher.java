package cn.willian.coding.designmode.structure.decorator.impl;

import cn.willian.coding.designmode.structure.decorator.Cipher;
import cn.willian.coding.designmode.structure.decorator.CipherDecorator;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 11:13:26
 */
// ConcreteDecorator 具体装饰类
public class ComplexCipher extends CipherDecorator {

    public ComplexCipher(Cipher cipher) {
        super(cipher);
    }
    // 对原来的加密内容进行了增强
    // 调用了父类的 encrypt() 方法
    // 并通过新增的 reserve() 方法对加密后字符串做进一步处理
    public String encrypt(String content) {
        String result = super.encrypt(content);
        result = reverse(result);
        return result;
    }

    private String reverse(String text) {
        StringBuilder str = new StringBuilder();
        for (int i = text.length(); i > 0; i--) {
            str.append(text.charAt(i - 1));
        }
        return str.toString();
    }
}
