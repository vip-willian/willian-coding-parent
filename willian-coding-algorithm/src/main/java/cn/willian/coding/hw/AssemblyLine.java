package cn.willian.coding.hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-29 14:28:59
 */
// 序号9
// 标题：流水线
// 一个工厂有m条流水线来并行完成n个独立的作业，该工厂设置了一个调度系统，在安排作业时，总是优先执
// 行处理时间最短的作业。现给定流水线个数m，需要完成的作业个数作业数n，每个作业的处理时间分别为
// t1,t2...tn。请你编程计算处理完所有作业的耗时多少？
// 当n＞m时，首先处理时间短的m个作业进入流水线，其他的等待，当某个作业完成时，依次从剩余作业中取处理时间最短的进入处理。
//
// 输入描述
// 第一行为2个整数（采用空格分隔），分别表示流水线的个数m，和作业数n；
// 第二行输入n个整数（采用空格分隔），表示每个作业的处理时长t1,t2....tn。
// 0＜m,n＜100，0＜t1,t2...tn＜100。
// 注：保证输入都是合法的。
//
// 输出描述：
//
// 输出处理完所有作业的总时长
//
// 示例1：
// 输入
//
// 3 5
// 8 4 3 2 10
//
// 输出
//
// 13
public class AssemblyLine {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String m_n = sc.nextLine();
        String time = sc.nextLine();

        // 取出m条流水线 n个作业数量
        int m = Integer.parseInt(m_n.split(" ")[0]);
        int n = Integer.parseInt(m_n.split(" ")[1]);
        // 任务执行时长集合，并按执行时长排序
        List<Integer> taskTime =
            Arrays.stream(time.split(" ")).map(Integer::parseInt).sorted().collect(Collectors.toList());

        // 如果任务数量n小于等于流水线数量m
        if (n <= m) {
            // 总耗时为任务执行时间最长的那个
            System.out.println(taskTime.get(n - 1));
        }

        List<Integer> result = new ArrayList<>();

        // 取出前m个任务,放入生产线执行
        for (int i = 0; i < m; i++) {
            result.add(taskTime.get(i));
        }
        // 后面的n-m个任务 判断放入到哪个对应的流水线中
        for (int i = m; i < n; i++) {
            // 最短执行完成的最先放入，所以是轮询放入取余
            int index = i % m;
            // 重新设置执行时长，等于上批任务执行的总时长+当前任务执行时长
            result.set(index, result.get(index) + taskTime.get(i));
        }
        // 取执行耗时最久的生产线为总耗时
        Integer maxTime = result.stream().max(Integer::compareTo).orElse(null);
        System.out.println(maxTime);
    }
}
