package cn.willian.coding.designmode.structure.decorator;

import cn.willian.coding.designmode.structure.decorator.impl.AdvancedCipher;
import cn.willian.coding.designmode.structure.decorator.impl.ComplexCipher;
import cn.willian.coding.designmode.structure.decorator.impl.SimpleCipher;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 11:17:10
 */
public class DecoratorMain {

    public static void main(String[] args) {
        String password = "willian";   //明文
        String cpassword;   // 密文

        // 第一次: 通过简单加密算法进行加密
        Cipher sc = new SimpleCipher();
        cpassword = sc.encrypt(password);

        System.out.println("简单加密算法结果: " + cpassword);
        System.out.println("---------------------");

        // 第二次: 经过复杂加密算法包装
        Cipher cc = new ComplexCipher(sc);
        cpassword = cc.encrypt(password);
        System.out.println("(装饰)复杂加密算法结果: " + cpassword);
        System.out.println("---------------------");

        // 第三次: 继续对内容进行更高级加密算法包装
        Cipher ac = new AdvancedCipher(cc);
        cpassword = ac.encrypt(password);
        System.out.println("(装饰)高级加密算法结果: " + cpassword);
        System.out.println("---------------------");
    }
}
