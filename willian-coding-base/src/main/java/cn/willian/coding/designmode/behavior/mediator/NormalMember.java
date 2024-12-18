package cn.willian.coding.designmode.behavior.mediator;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 21:57:08
 */
public class NormalMember extends Member {

    public NormalMember(String name) {
        super(name);
    }

    @Override
    public void sendText(String to, String message) {
        System.out.println("普通会员发送消息:");
        chatRoom.sendText(name, to, message);
    }

    @Override
    public void sendImage(String to, String image) {
        System.out.println("普通会员发送图片:");
        chatRoom.sendImage(name, to, image);
    }
}
