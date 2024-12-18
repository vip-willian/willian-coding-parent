package cn.willian.coding.designmode.structure.bridge.impl.system;

import cn.willian.coding.designmode.structure.bridge.SystemImage;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 10:10:54
 */
public class MacSystemImageImpl implements SystemImage {

    @Override
    public void doPaint(Matrix matrix) {
        // 解析图像
        System.out.println("Mac System Image Paint X = " + matrix.getX() + " Y = " + matrix.getY());
        System.out.print("在Mac系统中显示图像: ");
    }
}
