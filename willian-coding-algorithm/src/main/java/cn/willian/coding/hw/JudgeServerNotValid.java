package cn.willian.coding.hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-29 16:46:11
 */
// 序号13
// 服务器失效判断
// 某系统中有众多服务，每个服务用字符串（只包含字母和数字，长度<=10)唯一标识，服务间可能有依赖关
// 系，如A依赖B，则当B故障时导致A也故障。
// 依赖具有传递性，如A依赖B，B依赖C，当C故障的时候会导致B故障，也会导致A故障。
// 给出所有依赖关系，以及当前已知的故障服务，要求输出所有正常的服务。
// 依赖关系：服务1-服务2表示“服务1”依赖“服务2”
// 不必考虑输入异常，用例保证：依赖关系列表、故障列表非空，且依赖关系数，故障服务数都不会超过
// 3000，服务标识格式正常。
// 输入描述：
// 半角逗号分隔的依赖关系列表（换行）
// 半角逗号分隔的故障服务列表
// 输出描述：
// 依赖关系列表中提及的所有服务中可以正常工作的服务列表，用半角逗号分隔，按依赖关系列表中出现的次
// 序排序。特别的，没有正常节点输出单独一个半角逗号。
//
// 示例1：
// 输入：
// a1-a2,a5-a6,a2-a3
// a5,a2
// 输出：
// a6,a3
public class JudgeServerNotValid {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] split = sc.nextLine().split(",");

        // 构建依赖关系,tree 主服务 能够影响到的服务
        Set<String> allService = new HashSet<>();
        Map<String, List<String>> tree = new HashMap<>();
        for (String relation : split) {
            // a1 依赖于 a2 a1是从服务器， a2是主服务器
            String slave = relation.split("-")[0];
            String master = relation.split("-")[1];
            allService.add(master);
            allService.add(slave);
            tree.computeIfAbsent(master, k -> new ArrayList<>()).add(slave);
        }

        // 获取无效的服务器
        Set<String> invalidService = getInvalidServices(sc, tree);
        List<String> remain = new ArrayList<>();
        for (String s : allService) {
            if (!invalidService.contains(s)) {
                remain.add(s);
            }
        }

        // 输出结果
        for (int i = 0; i < remain.size(); i++) {
            if (i != 0) {
                System.out.print(",");
            }
            System.out.print(remain.get(i));
        }
        System.out.println();
    }

    private static Set<String> getInvalidServices(Scanner sc, Map<String, List<String>> tree) {

        List<String> input = Arrays.stream(sc.nextLine().split(",")).collect(Collectors.toList());
        Set<String> invalidService = new HashSet<>();
        Stack<String> stack = new Stack<>();
        for (String s : input) {
            stack.push(s);
            invalidService.add(s);
        }
        while (!stack.isEmpty()) {
            String service = stack.pop();
            if (tree.containsKey(service)) {
                for (String s : tree.get(service)) {
                    if (!invalidService.contains(s)) {
                        invalidService.add(s);
                        stack.push(s);
                    }
                }
            }
        }
        return invalidService;
    }
}
