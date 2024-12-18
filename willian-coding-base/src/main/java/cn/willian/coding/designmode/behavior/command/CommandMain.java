package cn.willian.coding.designmode.behavior.command;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 18:16:32
 */
public class CommandMain {
    public static void main(String[] args) {

        Editor editor = new Editor("编辑器文本内容");
        Application application = new Application(editor);

        System.out.println("------第一组命令-------");
        // 1、复制命令
        application.execute(CommandEvent.COPY);
        System.out.println("执行完复制命令后 content = " + editor.getContent());
        // 2、粘贴命令
        application.execute(CommandEvent.PASTE);
        System.out.println("执行完粘贴命令后 content = " + editor.getContent());
        // 3、撤销命令
        application.execute(CommandEvent.UNDO);
        System.out.println("执行完撤销命令后 content = " + editor.getContent());

        System.out.println("------第二组命令-------");
        // 4、剪切命令
        application.execute(CommandEvent.CUT);
        System.out.println("执行完剪切命令后 content = " + editor.getContent());
        // 5、粘贴命令
        application.execute(CommandEvent.PASTE);
        System.out.println("执行完粘贴命令后 content = " + editor.getContent());

        System.out.println("------第三组命令-------");
        // 6、替换命令
        application.execute(CommandEvent.REPLACE);
        System.out.println("执行完替换命令后 content = " + editor.getContent());
        // 7、撤销命令
        application.execute(CommandEvent.UNDO);
        System.out.println("执行完撤销命令后 content = " + editor.getContent());
    }

}
