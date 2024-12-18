package cn.willian.coding.designmode.structure.composite;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 11:45:46
 */
public interface FileComponent {

    void add(FileComponent fle);

    void remove(FileComponent fle);

    FileComponent get(int i);

    void killVirus();
}
