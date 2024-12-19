package cn.willian.coding.designmode.behavior.strategy;

import cn.willian.coding.tools.XmlReadUtils;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 11:51:07
 */
public class StrategyMain {

    public static void main(String[] args) {
        MovieTicket mt = new MovieTicket();
        double originalPrice = 60.0;
        double currentPrice;

        mt.setPrice(originalPrice);
        System.out.println("原始价为：" + originalPrice);
        System.out.println("---------------------------------");

        Discount discount = (Discount)XmlReadUtils.getBean("movie-ticket.xml", "className"); // 读取配置文件并反射生成具体折扣对象
        mt.setDiscount(discount); // 注入折扣对象

        currentPrice = mt.getPrice();
        System.out.println("折后价为：" + currentPrice);
    }
}
