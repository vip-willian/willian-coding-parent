package cn.willian.coding.leetcode;

/**
 * 88. 合并两个有序数组
 * <p>
 * https://leetcode.cn/problems/merge-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * <p>
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * <p>
 * 输出：[1,2,2,3,5,6]
 * <p>
 * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
 * <p>
 * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * <p>
 * 输出：[1]
 * <p>
 * 解释：需要合并 [1] 和 [] 。
 * <p>
 * 合并结果是 [1] 。
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums1 = [0], m = 0, nums2 = [1], n = 1
 * <p>
 * 输出：[1]
 * <p>
 * 解释：需要合并的数组是 [] 和 [1] 。
 * <p>
 * 合并结果是 [1] 。
 * <p>
 * 注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-16 19:25:23
 */
public class MergeTwoSortArray {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        merge(nums1, 3, nums2, 3);
        for (int i : nums1) {
            System.out.println(i);
        }
    }

    // 逆向双指针
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        // 从后面开始依次将最大的插入进入
        int tail = m + n - 1;
        // 定义要插入的值
        int cur;
        while (p1 >= 0 || p2 >= 0) {
            // 代表num1数组先走完
            if (p1 == -1) {
                // 将剩余的num2数组的数据从大到小依次放入数据
                cur = nums2[p2--];
            }
            // 代表num2数组先走完
            else if (p2 == -1) {
                // 将剩余的num1数组的数据从大到小依次放入数据
                cur = nums1[p1--];
            }
            // 数组1的数据大于数组2的数据，优先放入
            else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            // 将数据放入num1数组末尾，从后往前放
            nums1[tail--] = cur;
        }
    }
}
