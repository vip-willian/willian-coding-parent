package cn.willian.coding.designmode.structure.bridge.impl.image;

import cn.willian.coding.designmode.structure.bridge.AbstractImage;
import cn.willian.coding.designmode.structure.bridge.SystemImage;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 10:14:11
 */
public class GIFImage extends AbstractImage {

    @Override
    public void parseFile(String fileName) {

        SystemImage.Matrix matrix = new SystemImage.Matrix();
        matrix.setX(88);
        matrix.setY(88);
        systemImage.doPaint(matrix);
        System.out.println(fileName + ", 图片格式为GIF");
    }
}
