package cn.willian.coding.leetcode;

/**
 * 27. 移除元素
 * <p>
 * https://leetcode.cn/problems/remove-element/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素。元素的顺序可能发生改变。然后返回 nums 中与 val 不同的元素的数量。
 * <p>
 * 假设 nums 中不等于 val 的元素数量为 k，要通过此题，您需要执行以下操作：
 * <p>
 * 更改 nums 数组，使 nums 的前 k 个元素包含不等于 val 的元素。nums 的其余元素和 nums 的大小并不重要。 返回 k。
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-16 19:45:29
 */
public class RemoveElement {

    public static void swap(int p1, int p2, int[] nums) {
        int temp = nums[p1];
        nums[p1] = nums[p2];
        nums[p2] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        int val = 3;
        // int k = removeElement1(nums, val);
        int k = removeElement2(nums, val);
        System.out.println(k);
        System.out.println("======");
        for (int i : nums) {
            System.out.println(i);
        }
    }

    public static int removeElement1(int[] nums, int val) {

        // 从数组后面往前找，交换数据
        int p1 = nums.length - 1;
        int p2 = nums.length - 1;
        while (p1 >= 0) {
            if (nums[p1] == val) {
                swap(p1, p2, nums);
                p1--;
                p2--;
            } else {
                p1--;
            }
        }
        return p2 + 1;
    }

    public static int removeElement2(int[] nums, int val) {

        int left = 0;
        int right = nums.length;

        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right - 1];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }
}
