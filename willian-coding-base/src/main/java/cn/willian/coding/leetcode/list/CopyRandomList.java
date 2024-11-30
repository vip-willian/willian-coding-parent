package cn.willian.coding.leetcode.list;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Objects;

import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-30 12:28:09
 */
public class CopyRandomList {

    public static void main(String[] args) {

        RandomNode head = buildRandomNode();

        RandomNode newHead = copyRandomNode(head);
        System.out.println(newHead.getVal());
    }

    private static RandomNode copyRandomNode(RandomNode head) {

        RandomNode cur = head;
        // 将元素放入数组中
        Map<RandomNode, RandomNode> map = new HashMap<>();
        while (cur != null) {
            map.put(cur, new RandomNode(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    private static RandomNode buildRandomNode() {

        RandomNode node3 = new RandomNode(3);
        RandomNode node2 = new RandomNode(2);
        RandomNode node1 = new RandomNode(1);

        node1.next = node2;
        node1.rand = node3;

        node2.next = node3;
        node2.rand = node1;

        node3.rand = node2;

        return node1;
    }

    @Data
    public static class RandomNode {

        public int val;
        public RandomNode next;
        public RandomNode rand;

        @Override
        public boolean equals(Object object) {
            if (object == null || getClass() != object.getClass()) return false;
            RandomNode that = (RandomNode) object;
            return val == that.val && Objects.equal(next, that.next) && Objects.equal(rand, that.rand);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(val, next, rand);
        }

        RandomNode() {}

        RandomNode(int val) {
            this.val = val;
        }

        RandomNode(int val, RandomNode next, RandomNode rand) {
            this.val = val;
            this.next = next;
            this.rand = rand;
        }
    }
}
