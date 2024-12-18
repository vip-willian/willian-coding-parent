package cn.willian.coding.designmode.structure.decorator.impl;

import cn.willian.coding.designmode.structure.decorator.Cipher;
import cn.willian.coding.designmode.structure.decorator.CipherDecorator;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 11:15:09
 */
public class AdvancedCipher extends CipherDecorator {

    public AdvancedCipher(Cipher cipher) {
        super(cipher);
    }

    // 调用了父类的 encrypt() 方法
    // 并通过新增的 mod() 方法对加密后字符串做进一步处理
    @Override
    public String encrypt(String content) {
        String result = super.encrypt(content);
        result = mod(result);
        return result;
    }

    private String mod(String text) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            String c = String.valueOf(text.charAt(i) % 6);
            str.append(c);
        }
        return str.toString();
    }
}
