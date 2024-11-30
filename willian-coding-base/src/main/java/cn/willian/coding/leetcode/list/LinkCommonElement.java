package cn.willian.coding.leetcode.list;

/**
 * 打印链表公共元素
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-30 10:04:27
 */
public class LinkCommonElement {

    public static void main(String[] args) {
        ListNode node1 = ListNode.buildNode1();
        ListNode node2 = ListNode.buildNode2();
        printCommonNode(node1, node2);
    }

    private static void printCommonNode(ListNode node1, ListNode node2) {

        while (node1 != null && node2 != null) {
            int nodeValue1 = node1.getVal();
            int nodeValue2 = node2.getVal();
            if (nodeValue1 == nodeValue2) {
                System.out.println(nodeValue1);
                node1 = node1.next;
                node2 = node2.next;
            } else if (nodeValue1 > nodeValue2) {
                node2 = node2.next;
            } else {
                node1 = node1.next;
            }
        }
    }
}
