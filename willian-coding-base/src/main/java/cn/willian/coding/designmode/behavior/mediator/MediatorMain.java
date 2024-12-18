package cn.willian.coding.designmode.behavior.mediator;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 21:59:43
 */
public class MediatorMain {
    public static void main(String[] args) {
        ChatRoomMediator chatRoom = new ChatRoom("开心快乐群");

        Member m1, m2, m3, m4, m5;
        m1 = new DiamondMember("Willian");
        m2 = new DiamondMember("Light");
        m3 = new NormalMember("Jack");
        m4 = new NormalMember("Mary");
        m5 = new NormalMember("Tom");

        chatRoom.register(m1);
        chatRoom.register(m2);
        chatRoom.register(m3);
        chatRoom.register(m4);
        chatRoom.register(m5);

        m1.sendText("Light", "Light，你好");
        m2.sendText("Willian", "Willian，你好");
        m1.sendText("Mary", "今天天气不错，有日");
        m2.sendImage("Tom", "一个很大很大的太阳");
        m2.sendImage("Tom", "太阳");
        m3.sendText("Willian", "还有问题吗？");
        m3.sendText("Mary", "还有问题吗？");
        m4.sendText("Jack", "没有了，谢谢");
        m5.sendText("Light", "我也没有了");
        m5.sendImage("Light", "谢谢");
    }
}
