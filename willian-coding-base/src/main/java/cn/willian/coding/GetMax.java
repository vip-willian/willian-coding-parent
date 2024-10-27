package cn.willian.coding;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-11 11:20:59
 */
public class GetMax {

    public static int getMax(int[] arr, int l, int r) {

        if (l == r) {
            return arr[l];
        }
        int mid = l + ((r - l) >> 1);
        int leftMax = getMax(arr, l, mid);
        int rightMax = getMax(arr, mid + 1, r);
        return Math.max(leftMax, rightMax);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(getMax(arr, 0, arr.length - 1));
    }
}
