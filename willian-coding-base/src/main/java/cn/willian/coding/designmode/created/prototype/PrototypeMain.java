package cn.willian.coding.designmode.created.prototype;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-13 10:22:10
 */
public class PrototypeMain {
    public static void main(String[] args) {

        // 创建原型对象
        WeekLog firstWeekLog = new WeekLog();
        firstWeekLog.setName("张无忌");
        firstWeekLog.setDate("第1周");
        firstWeekLog.setContent("这周工作很忙，每天加班到夜里！");

        WeekAttachment attachment = new WeekAttachment();
        attachment.setFileName("工作周报.doc");
        firstWeekLog.setAttachment(attachment);

        System.out.println("****周报****");
        System.out.println("日期：" + firstWeekLog.getDate());
        System.out.println("姓名：" + firstWeekLog.getName());
        System.out.println("内容：" + firstWeekLog.getContent());
        System.out.println("附件：" + firstWeekLog.getAttachment().getFileName());
        System.out.println("--------------------------------");

        // 【浅拷贝】调用克隆方法创建克隆对象
        WeekLog secondWeekLog = firstWeekLog.clone();
        secondWeekLog.setDate("第2周");
        System.out.println("****周报****");
        System.out.println("日期：" + secondWeekLog.getDate());
        System.out.println("姓名：" + secondWeekLog.getName());
        System.out.println("内容：" + secondWeekLog.getContent());
        System.out.println("附件：" + secondWeekLog.getAttachment().getFileName());

        System.out.println("第1周和第2周是否同一份周报: " + (firstWeekLog == secondWeekLog));
        System.out.println("第1周和第2周是否同一份附件: " + (firstWeekLog.getAttachment() == secondWeekLog.getAttachment()));

        // 【深拷贝】调用深拷贝方法获取对象
        WeekLog thirdWeekLog = secondWeekLog.deepClone();
        thirdWeekLog.setDate("第3周");
        System.out.println("****周报****");
        System.out.println("日期：" + thirdWeekLog.getDate());
        System.out.println("姓名：" + thirdWeekLog.getName());
        System.out.println("内容：" + thirdWeekLog.getContent());
        System.out.println("附件：" + thirdWeekLog.getAttachment().getFileName());

        System.out.println("第2周和第3周是否同一份周报: " + (secondWeekLog == thirdWeekLog));
        System.out.println("第2周和第3周是否同一份附件: " + (secondWeekLog.getAttachment() == thirdWeekLog.getAttachment()));
    }
}
