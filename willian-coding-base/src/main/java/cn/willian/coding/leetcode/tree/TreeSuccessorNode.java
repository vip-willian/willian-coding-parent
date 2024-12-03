package cn.willian.coding.leetcode.tree;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-30 22:40:03
 */
public class TreeSuccessorNode {

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.init();

        TreeNode result = getSuccessorNode(treeNode);
        System.out.println(result);
    }

    public static TreeNode getSuccessorNode(TreeNode node) {

        if (node == null) {
            return node;
        }
        // 有右子树，中序后继节点为右子树下的最后一个左节点
        if (node.right != null) {
            return getLeftMost(node.right);
        }
        // 没右子树，一直向上查找，判断当前节点是否为父节点的左子节点
        TreeNode parent = node.parent;
        while (parent != null && parent.left != node) {
            node = parent;
            parent = node.parent;
        }
        return parent;
    }

    private static TreeNode getLeftMost(TreeNode node) {

        if (node == null) {
            return node;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
