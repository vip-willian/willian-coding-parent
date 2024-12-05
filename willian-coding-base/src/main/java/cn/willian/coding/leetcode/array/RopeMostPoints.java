package cn.willian.coding.leetcode.array;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-04 17:53:57
 */
// 给定一个有序数组arr，代表坐落在X轴上的点，给定一个正数K，代表绳子的长度。返回绳子最多压中几个点？即使绳子边缘处盖住点也算盖住。
public class RopeMostPoints {

    public static void main(String[] args) {
        int[] numbers = {2, 4, 8, 9, 12, 17};
        int K = 5;
        System.out.println(maxPoints(numbers, K));
    }

    public static int maxPoints(int[] numbers, int K) {

        int left = 0;
        int right = 0;
        int n = numbers.length;
        int maxPoint = 0;
        while (left < n) {
            while (right < n && numbers[right] - numbers[left] <= K) {
                right++;
            }
            maxPoint = Math.max(maxPoint, right - left);
            left++;
        }
        return maxPoint;
    }
}
