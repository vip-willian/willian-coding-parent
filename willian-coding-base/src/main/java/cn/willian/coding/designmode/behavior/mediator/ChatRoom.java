package cn.willian.coding.designmode.behavior.mediator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 21:44:39
 */
public class ChatRoom implements ChatRoomMediator {

    private final String chatRoomName;
    private final Map<String, Member> memberMap;

    public ChatRoom(String chatRoomName) {
        this.chatRoomName = chatRoomName;
        this.memberMap = new HashMap<>();
    }

    @Override
    public void register(Member member) {

        if (!memberMap.containsKey(member.getName())) {
            memberMap.put(member.getName(), member);
            member.setChatRoom(this);
        }
    }

    @Override
    public void sendText(String from, String to, String message) {

        Member member = memberMap.get(to);
        String newMessage = message.replaceAll("日", "*");
        member.receiveText(from, newMessage);
    }

    @Override
    public void sendImage(String from, String to, String image) {
        Member member = memberMap.get(to);
        if (image.length() > 5) {
            System.out.println("图片太大，发送失败");
        } else {
            member.receiveImage(from, image);
        }
    }
}
