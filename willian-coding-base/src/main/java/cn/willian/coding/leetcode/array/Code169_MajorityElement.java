package cn.willian.coding.leetcode.array;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-03 10:49:22
 */
// 169. 多数元素
// https://leetcode.cn/problems/majority-element/description
public class Code169_MajorityElement {

    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        int element = majorityElement(nums);
        System.out.println(element);
    }

    public static int majorityElement(int[] nums) {

        int count = 0;
        // 候选元素
        int candidate = -1;
        for (int num : nums) {
            // 如果count == 0 有2层含义
            // 第一层，初始化进来时，赋值初始值 = 数组第一个元素
            // 第二层，在遍历过程中，另外一个多的元素将其重新减为0
            if (count == 0) {
                candidate = num;
            }
            // 如果元素值等于候选者 元素个数++
            // 如果元素值不等于候选者 元素个数--
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }
}
