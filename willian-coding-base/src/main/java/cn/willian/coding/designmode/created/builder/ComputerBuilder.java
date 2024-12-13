package cn.willian.coding.designmode.created.builder;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-13 11:23:52
 */
public abstract class ComputerBuilder {

    protected final Computer computer = new Computer();

    /**
     * 构建CPU
     */
    public abstract ComputerBuilder cpu(String core);

    /**
     * 构建内存
     */
    public abstract ComputerBuilder memory(String memory);

    /**
     * 构建硬盘
     */
    public abstract ComputerBuilder hardDisk(String capacity);

    /**
     * 构建鼠标
     */
    public abstract ComputerBuilder mouse();

    /**
     * 构建键盘
     */
    public abstract ComputerBuilder keyboard();

    /**
     * 构建显示器
     */
    public abstract ComputerBuilder display();

    /**
     * 组装电脑
     */
    public abstract Computer build();
}
