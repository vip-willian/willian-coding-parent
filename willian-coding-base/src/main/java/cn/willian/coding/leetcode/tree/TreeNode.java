package cn.willian.coding.leetcode.tree;

import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-29 20:54:19
 */
@Data
public class TreeNode {

    /**
     * 当前值
     */
    public int value;
    /**
     * 左节点
     */
    public TreeNode left;
    /**
     * 右节点
     */
    public TreeNode right;

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.right = right;
        this.left = left;
        this.value = value;
    }

    public static TreeNode init() {

        TreeNode one = new TreeNode(1, null, null);
        TreeNode two = new TreeNode(2, null, null);
        TreeNode eight = new TreeNode(8, null, null);
        TreeNode three = new TreeNode(3, one, two);
        TreeNode five = new TreeNode(5, null, null);
        TreeNode four = new TreeNode(4, three, five);
        TreeNode nine = new TreeNode(9, eight, null);
        return new TreeNode(6, four, nine);
    }
}