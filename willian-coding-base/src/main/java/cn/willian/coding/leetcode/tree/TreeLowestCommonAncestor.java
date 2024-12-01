package cn.willian.coding.leetcode.tree;

/**
 * 最小公共祖先
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-30 22:14:12
 */
@SuppressWarnings("all")
public class TreeLowestCommonAncestor {

    public static void main(String[] args) {
        TreeNode one = new TreeNode(1, null, null);
        TreeNode two = new TreeNode(2, null, null);
        TreeNode eight = new TreeNode(8, null, null);
        TreeNode three = new TreeNode(3, one, two);
        TreeNode five = new TreeNode(5, null, null);
        TreeNode four = new TreeNode(4, three, five);
        TreeNode nine = new TreeNode(9, eight, null);
        TreeNode root = new TreeNode(6, four, nine);

        TreeNode result = lowestCommonAncestor(root, one, five);
        System.out.println("最小公共祖先：" + result);
    }

    public static TreeNode lowestCommonAncestor(TreeNode head, TreeNode node1, TreeNode node2) {

        // node1节点在node2节点上面 || node2节点在node1节点上面 ，则要么就是node1是最小公共祖先或者node2是最小公共祖先
        if (head == null || head == node1 || head == node2) {
            return head;
        }
        TreeNode left = lowestCommonAncestor(head.left, node1, node2);
        TreeNode right = lowestCommonAncestor(head.right, node1, node2);
        // 左右节点数据都不为空时，说明node1和node2有公共的父节点head
        if (left != null && right != null) {
            return head;
        }
        // 左右节点有一方为空时，说明node1和node2只有一个在这个树枝上，返回不为空的那个
        return left != null ? left : right;
    }
}
