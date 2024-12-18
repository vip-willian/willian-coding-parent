package cn.willian.coding.designmode.behavior.mediator;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 21:44:39
 */
public interface ChatRoomMediator {

    /**
     * 注册成员
     */
    void register(Member member);

    /**
     * 发送文本消息
     */
    void sendText(String from, String to, String message);

    /**
     * 发送图片消息
     */
    void sendImage(String from, String to, String image);
}
