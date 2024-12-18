package cn.willian.coding.designmode.structure.bridge;

import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 10:06:13
 */
public interface SystemImage {

    /**
     * 显示像素矩阵
     *
     * @param matrix 矩阵信息
     */
    void doPaint(Matrix matrix);

    @Data
    class  Matrix{
        private Integer X;
        private Integer Y;
    }
}

