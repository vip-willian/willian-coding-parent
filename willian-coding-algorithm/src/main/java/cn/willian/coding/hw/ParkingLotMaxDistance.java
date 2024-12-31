package cn.willian.coding.hw;

import java.util.Scanner;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-31 17:42:50
 */
// 停车场最大距离
// 题目描述
// 停车场有一横排车位0代表没有停车,1代表有车.
// 至少停了一辆车在车位上,也至少有一个空位没有停车.
// 为防止刮蹭,需为停车人找到一个车位
// 使得停车人的车最近的车辆的距离是最大的
// 返回此时的最大距离
//
// 输入描述
// 一个用半角逗号分割的停车标识字符串,停车标识为0或1,
// 0为空位,1为已停车
// 停车位最多有100个
// 输出描述
// 输出一个整数记录最大距离
// 示例一
// 输入
// 1,0,0,0,0,1,0,0,1,0,1
// 输出
// 2
// 说明
// 当车停在第三个位置上时,离其最近的车距离为2(1~3)
// 当车停在第四个位置上时,离其最近的车距离为2(4~6)
// 其他位置距离为1
// 因此最大距离为2
public class ParkingLotMaxDistance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] parking = sc.nextLine().split(",");
            System.out.println(getMaxDistance(parking));
        }
    }

    // 找到两个1之间0空位最多的位置向上取整
    private static int getMaxDistance(String[] parking) {

        // 计数, 空车位的数量
        int count = 0;
        // 最大距离
        int maxDistance = 0;
        // 左侧标记
        int left = 0;
        for (String s : parking) {
            // 如果是空车位，计数器+1
            if ("0".equals(s)) {
                count++;
            } else {
                // 场景一: 0000000100010101
                if (left == 0) {
                    maxDistance = count * 2;
                    left = 1;
                } else {
                    // 场景二: 100100000001001
                    maxDistance = Math.max(maxDistance, count);
                }
                count = 0;
            }
        }
        // 场景三: 100100100000000
        if (parking[parking.length - 1].equals("0")) {
            maxDistance = Math.max(maxDistance, count * 2);
        } else {
            maxDistance = Math.max(maxDistance, count);
        }
        // 最大距离除以2，然后向上取整，得到最大距离的结果
        return (maxDistance + 1) / 2;
    }
}
