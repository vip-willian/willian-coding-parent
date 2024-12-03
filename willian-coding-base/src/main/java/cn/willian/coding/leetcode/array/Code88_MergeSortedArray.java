package cn.willian.coding.leetcode.array;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-03 09:34:04
 */
// 88. 合并两个有序数组
// https://leetcode.cn/problems/merge-sorted-array/description
public class Code88_MergeSortedArray {

    public static void main(String[] args) {

        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;

        merge(nums1, m, nums2, n);
    }

    // 逆向双指针 从后往前遍历
    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        int p1 = m - 1;
        int p2 = n - 1;
        int tail = m + n - 1;
        int cur;
        while (p1 >= 0 || p2 >= 0) {
            // 说明num1数组先遍历完了，把剩余num2数组元素放入
            if (p1 == -1) {
                cur = nums2[p2--];
            }
            // 说明num2数组先遍历完了，把剩余num1数组元素放入
            else if (p2 == -1) {
                cur = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                // 将num1数组元素， 放入num1数组后面,p1指针往前移动
                cur = nums1[p1--];
            } else {
                // 将num2数组元素， 放入num1数组后面,p2指针往前移动
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
    }
}
