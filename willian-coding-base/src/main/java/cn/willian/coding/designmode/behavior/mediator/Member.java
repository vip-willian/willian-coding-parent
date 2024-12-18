package cn.willian.coding.designmode.behavior.mediator;

import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 21:45:38
 */
@Data
public abstract class Member {

    // 抽象同事，持有中介的引用
    protected ChatRoomMediator chatRoom;
    protected String name;

    public Member(String name) {
        this.name = name;
    }

    /**
     * 发送文本消息
     */
    public abstract void sendText(String to, String message);

    /**
     * 发送图片消息
     */
    public abstract void sendImage(String to, String image);

    public void receiveText(String from, String message) {
        System.out.println(from + "发送文本给" + this.name + ",内容为：" + message);
    }

    public void receiveImage(String from, String image) {
        System.out.println(from + "发送图片给" + this.name + ",内容为：" + image);
    }
}
