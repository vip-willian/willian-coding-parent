package cn.willian.coding.tools;

import cn.binarywang.tools.generator.ChineseAddressGenerator;
import cn.binarywang.tools.generator.ChineseIDCardNumberGenerator;
import cn.binarywang.tools.generator.ChineseNameGenerator;

/**
 * @author <a href="mailto:wangyan@cai-inc.com">沐风</a>
 * @datetime 2024-09-06 09:32:15
 */
public class Randoms {

    /**
     * 生成中文名称
     */
    public static String getName() {

        return ChineseNameGenerator.getInstance().generate();
    }

    /**
     * 生成身份证号
     */
    public static String getIdCard() {

        return ChineseIDCardNumberGenerator.getInstance().generate();
    }

    /**
     * 生成性别
     */
    public static int getSex(String idCard) {

        return idCard.charAt(idCard.length() - 2) % 2 == 0 ? 0 : 1;
    }

    /**
     * 生成地址
     */
    public static String getAddress() {

        return ChineseAddressGenerator.getInstance().generate();
    }

    /**
     * 生成身高
     */
    public static int getHeight() {

        return getNum(150, 200);
    }

    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }
}
