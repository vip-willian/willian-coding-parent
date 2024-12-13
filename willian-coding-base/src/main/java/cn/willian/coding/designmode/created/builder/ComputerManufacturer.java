package cn.willian.coding.designmode.created.builder;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-13 11:42:38
 */
public class ComputerManufacturer {

    private final ComputerBuilder builder;

    public ComputerManufacturer(ComputerBuilder builder) {
        this.builder = builder;
    }

    public Computer build(String core, String memory, String capacity) {

        return builder.cpu(core).memory(memory).hardDisk(capacity).mouse().keyboard().display().build();
    }
}
