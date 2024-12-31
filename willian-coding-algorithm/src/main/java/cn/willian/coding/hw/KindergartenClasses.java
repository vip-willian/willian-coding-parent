package cn.willian.coding.hw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-31 23:12:41
 */
// 题目描述
// 幼儿园两个班的小朋友在排队时混在了一起，每位小朋友都知道自己是否与前面一位小朋友是否同班，请你帮忙把同班的小朋友找出来。
//
// 小朋友的编号为整数，与前一位小朋友同班用Y表示，不同班用N表示。
//
// 输入描述
// 输入为空格分开的小朋友编号和是否同班标志。 比如:6/N 2/Y 3/N 4/Y，表示共4位小朋友，2和6同班，3和2不同班，4和3同班。 其中，小朋友总数不超过999，每个小朋友编号大于0，小于等于999。 不考虑输入格式错误问题。
//
// 输出描述
// 输出为两行，每一行记录一个班小朋友的编号，编号用空格分开。且:
//
// 1、编号需要按照大小升序排列，分班记录中第一个编号小的排在第一行。
//
// 2、若只有一个班的小朋友，第二行为空行。
//
// 3、若输入不符合要求，则直接输出字符串ERROR。
//
// 示例1
// 输入：
// 1/N 2/Y 3/N 4/Y
//
// 输出：
// 1 2
// 3 4
//
// 说明：
// 2的同班标记为Y，因此和1同班。
// 3的同班标记为N，因此和1、2不同班。
// 4的同班标记为Y，因此和3同班。
// 所以1、2同班，3、4同班，输出为
// 1 2
// 3 4
public class KindergartenClasses {
    // 当前班级标记，0 表示班级A， 1 表示班级B
    private static int currentClass = -1;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // 1、输入处理与验证
        String inputLine = validAndGetInputLine(sc);
        if (inputLine.isEmpty()) {
            System.out.println("ERROR");
            return;
        }
        String[] tokens = inputLine.split(" ");
        // 2、班级分配
        // 初始化两个班级的列表
        List<Integer> classA = new ArrayList<>();
        List<Integer> classB = new ArrayList<>();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            // 校验输入
            Children children = validAndGetChildren(token, i);
            if (null == children) {
                System.out.println("ERROR");
                return;
            }
            // 处理班级
            handleClass(children, i, classA, classB);
        }

        // 3、排序与输出
        Collections.sort(classA);
        Collections.sort(classB);
        printClass(classA);
        printClass(classB);
    }

    private static void handleClass(Children children, int i, List<Integer> classA, List<Integer> classB) {

        Integer number = children.number;
        String flag = children.flag;

        // 处理第一个小朋友
        if (0 == i) {
            currentClass = 0;
            classA.add(number);
        } else {
            // 从第二个小朋友开始，判断标记
            // 与前面一个属于同一个班级
            if (flag.equals("Y")) {
                if (currentClass == 0) {
                    classA.add(number);
                } else {
                    classB.add(number);
                }
            } else {
                // 与前面一个不属于同一个班级
                // 切换班级
                currentClass = 1 - currentClass;
                if (currentClass == 0) {
                    classA.add(number);
                } else {
                    classB.add(number);
                }
            }
        }
    }

    private static Children validAndGetChildren(String token, int i) {

        // 检查是否包含了”/“
        if (!token.contains("/")) {
            return null;
        }
        // 分割编号和标志
        String[] parts = token.split("/");
        if (parts.length != 2) {
            return null;
        }
        String numStr = parts[0].trim();
        int number;
        try {
            number = Integer.parseInt(numStr);
        } catch (NumberFormatException e) {
            return null;
        }
        // 检查编号是否在[0~999]之间
        if (number < 0 || number > 100) {
            return null;
        }
        String flag = parts[1].trim();
        if (!flag.equals("Y") && !flag.equals("N")) {
            return null;
        }
        // 第一个小朋友，标记必须是“N”,表示一个新班级
        if (i == 0 && !flag.equals("N")) {
            return null;
        }
        return new Children(number, flag);
    }

    private static String validAndGetInputLine(Scanner sc) {

        if (!sc.hasNextLine()) {
            sc.close();
            return "";
        }
        String inputLine = sc.nextLine().trim();
        sc.close();
        return inputLine;
    }

    private static void printClass(List<Integer> classes) {

        for (int i = 0; i < classes.size(); i++) {
            System.out.print(classes.get(i));
            if (i != classes.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    static class Children {

        /**
         * 编号
         */
        private final Integer number;
        /**
         * 标记
         */
        private final String flag;

        public Children(Integer number, String flag) {
            this.number = number;
            this.flag = flag;
        }
    }
}
