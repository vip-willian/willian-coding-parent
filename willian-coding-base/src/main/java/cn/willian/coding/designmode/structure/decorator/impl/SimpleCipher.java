package cn.willian.coding.designmode.structure.decorator.impl;

import cn.willian.coding.designmode.structure.decorator.Cipher;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 11:10:22
 */
// ConcreteComponent 具体组件
public class SimpleCipher implements Cipher {

    @Override
    public String encrypt(String content) {
        String str = "";
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (c >= 'a' && c <= 'z') {
                c += 6;
                if (c > 'z') c -= 26;
                if (c < 'a') c += 26;
            }
            if (c >= 'A' && c <= 'Z') {
                c += 6;
                if(c > 'Z') c -= 26;
                if(c < 'A') c += 26;
            }
            str += c;
        }
        return str;
    }
}
