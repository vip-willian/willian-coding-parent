package cn.willian.coding.hw;

import java.util.Scanner;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-29 15:23:07
 */
// 序号：11
// 标题：【We are a team】
// 总共n个人在机房，每个人有一个标号（1＜＝标号＜＝n），他们分成了多个团队，需要你根据收到的m
// 条消息判定指定的两个人是否在一个团队中，具体的：
// 1、消息构成为:a b c，整数a、b分别代表了两个人的标号，整数c代表指令。
// 2、c==0代表a和b在一个团队内。
// 3、c==1代表需要判定a和b的关系，如果a和b是一个团队，输出一行“we are a team”
// ，如果不是，输出
// 一行“we are not a team ”
// 。
// 4、c为其他值，或当前行a或b超出1～n的范围，输出“da pian zi”
//
// 输入描述：
// 1、第一行包含两个整数n、m（1＜=n，m＜=100000），分别表示有n个人和m条消息。
// 2、随后的m行，每行一条消息，消息格式为：a b c（1＜=a，b＜=n，0＜=c＜=1）。
//
// 输出描述：
//
// 1、c==1时，根据a和b是否在一个团队中输出一行字符串，在一个团队中输出“we are a team”
// 个团队中输出“we are not a team”
// 。
// ，不在一
// 2、c为其他值，或当前行a或b的标号小于1或者大于n时，输出字符串“da pian zi”
// 3、如果第一行n和m的值超出约定的范围时，输出字符串“NULL”
//
// 示例1:
//
// 输入
// 5 6
// 1 2 0
// 1 2 1
// 1 5 0
// 2 3 1
// 2 5 1
// 1 3 2
//
// 输出
//
// we are a team
// we are not a team
// we are a team
// da pian zi
public class WeAreTeam {

    // 定义最大的上下
    static final int N = 100000 + 10;
    // 定义并查集数组
    static int[] p = new int[N];

    // 查找 x 所在的集合根节点
    // 使用路径压缩算法
    public static int find(int x) {
        if (p[x] == x) {
            return x;
        }
        p[x] = find(p[x]);
        return p[x];
    }

    public static void merge(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa != pb) {
            p[pa] = pb;
        }
    }

    public static boolean isSame(int a, int b) {
        return find(a) == find(b);
    }

    // 并查集
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // n个人
        int n = sc.nextInt();
        // m条消息
        int m = sc.nextInt();

        if (n < 1 || n > 100000 || m < 1 || m > 100000) {
            System.out.println("NULL");
            return;
        }

        // 初始化并查集,每个人的父节点指向自己
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
        // 根据每一条不同的消息进行处理
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            // 如果输入的不合法，输出 ”da pian zi“
            if (a < 1 || a > n || b < 1 || b > n || (c != 0 && c != 1)) {
                System.out.println("da pian zi");
                continue;
            }
            // 执行合并操作， 将 a,b合并到同一个团队
            if (c == 0) {
                merge(a, b);
            } else {
                if (isSame(a, b)) {
                    System.out.println("we are a team");
                } else {
                    System.out.println("we are not a team");
                }
            }
        }
    }
}
