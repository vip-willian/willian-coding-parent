package cn.willian.coding.hw;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-29 14:59:05
 */
// 序号10
// 标题：磁盘容量排序
// 磁盘的容量单位常用的有M、G、T这三个等级，它们之间的换算关系为1T＝1024G，1G＝1024M。现
// 在给定n块磁盘的容量，请对它们按从小到大的顺序进行稳定排序。例如，给定5块盘的容量，1T，20M，3G，
// 10G6T，3M12G9M，排序后的结果为，20M，3G，3M12G9M，1T，10G6T。注意单位可以重复出现，上述
// 3M12G9M表示的容量即为3M+12G+9M，和12M12G相等。
//
// 输入描述：
// 输入第一行包含一个整数n（2＜＝n＜＝100），表示磁盘的个数，接下的n行，每行一个字符串（长度大于
// 2,小于30），表示磁盘的容量，由一个或多个格式为mv的子串组成，其中m表示容量大小，v表示容量单位，
// 例如20M，1T，30G，10G6T，3M12G9M。
// 磁盘容量m的范围为1到1024的正整数，容量单位v的范围只包含题目中提到的M,G,T三种，换算关系如
// 题目描述。
//
// 输出描述：
//
// 输出n行，表示n块磁盘容量排序后的结果。
//
// 示例1:
// 输入
// 3
// 1G
// 2G
// 1024M
//
// 输出
// 1G
// 1024G
// 2G
public class DiskCapacitySort {

    private static final Pattern pattern = Pattern.compile("(\\d+)([MGT])");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        List<String> disk = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            disk.add(sc.nextLine().trim());
        }
        // 稳定排序
        disk.sort(Comparator.comparingLong(DiskCapacitySort::convert));
        for (String s : disk) {
            System.out.println(s);
        }
    }

    private static long convert(String s) {

        long total = 0;
        Matcher m = pattern.matcher(s);
        while (m.find()) {
            long num = Long.parseLong(m.group(1));
            char unit = m.group(2).charAt(0);
            if (unit == 'M') {
                total += num;
            } else if (unit == 'G') {
                total += num * 1024;
            } else if (unit == 'T') {
                total += num * 1024 * 1024;
            }
        }
        return total;
    }
}
