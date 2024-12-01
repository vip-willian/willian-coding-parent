package cn.willian.coding.leetcode.graph;

import lombok.AllArgsConstructor;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-01 18:27:07
 */
@AllArgsConstructor
public enum GraphTypeEnum {

    ADJ_LIST("邻接表"),
    ADJ_MATRIX("邻接矩阵"),
    CUSTOM("自定义图结构"),

    ;

    private  final String desc;
}
