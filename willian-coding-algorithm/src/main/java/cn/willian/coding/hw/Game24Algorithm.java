package cn.willian.coding.hw;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-29 23:00:14
 */
// 给出4个1-10的数字，通过加减乘除运算，得到数字为24就算胜利,除法指实数除法运算,运算符仅允许出现在两个数字之间,
// 本题对数字选取顺序无要求，但每个数字仅允许使用一次，且需考虑括号运算 此题允许数字重复，如3 3 4 4为合法输入，
// 此输入一共有两个3，但是每个数字只允许使用一次，则运算过程中两个3都被选取并进行对应的计算操作。
//
// 输入描述： 读入4个[1,10]的整数，数字允许重复，测试用例保证无异常数字。
//
// 输出描述： 对于每组案例，输出一行表示能否得到24点，能输出true，不能输出false

// 输入：
// 7 2 1 10
// 输出：
// true
public class Game24Algorithm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            List<Integer> nums =
                Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            boolean isGet24 = isGet24(nums);
            System.out.println(isGet24);
        }
    }

    private static boolean isGet24(List<Integer> nums) {

        boolean isGet24 = false;
        boolean[] visited = new boolean[4];
        for (int i = 0; i < 4; i++) {
            visited[i] = true;
            if (dfs(nums, visited, nums.get(i))) {
                isGet24 = true;
                break;
            }
        }
        return isGet24;
    }

    private static boolean dfs(List<Integer> nums, boolean[] visited, Integer value) {

        for (int i = 0; i < nums.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (
                // 加法
                dfs(nums, visited, value + nums.get(i)) ||
                // 减法
                    dfs(nums, visited, value - nums.get(i)) ||
                    // 乘法
                    dfs(nums, visited, value * nums.get(i)) ||
                    // 除法
                    (value % nums.get(i) == 0 && dfs(nums, visited, value / nums.get(i)))) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return value == 24;
    }
}
