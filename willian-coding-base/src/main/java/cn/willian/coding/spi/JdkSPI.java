package cn.willian.coding.spi;

import java.util.ServiceLoader;

/**
 *     -Xmx4g 最大堆内存
 *     –Xms4g 初始堆内存
 *     –Xmn1200m 新生代内存
 *     –Xss512k 栈大小
 *     -XX:NewRatio=4 年轻与老年比例
 *     -XX:SurvivorRatio=8
 *     -XX:PermSize=100m
 *     -XX:MaxPermSize=256m
 *     -XX:MaxTenuringThreshold=15
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-17 10:57:36
 */


public class JdkSPI {

    public static void main(String[] args) {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        for (Robot robot : serviceLoader) {
            robot.sayHello();
        }
    }
}
