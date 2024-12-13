package cn.willian.coding.designmode.created.builder;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-13 11:23:52
 */
public class DellComputerBuilder extends ComputerBuilder {

    @Override
    public ComputerBuilder cpu(String core) {
        ComputerCpu dellCpu = new ComputerCpu();
        dellCpu.setCore("4核");
        dellCpu.setBrand("因特尔");
        computer.setCpu(dellCpu);
        return this;
    }

    @Override
    public ComputerBuilder memory(String memory) {

        computer.setMemory(memory);
        return this;
    }

    @Override
    public ComputerBuilder hardDisk(String capacity) {
        ComputerHardDisk macHardDisk = new ComputerHardDisk();
        macHardDisk.setType("HDD");
        macHardDisk.setCapacity(capacity);
        computer.setHardDisk(macHardDisk);
        return this;
    }

    @Override
    public ComputerBuilder mouse() {
        computer.setMouse("罗技鼠标");
        return this;
    }

    @Override
    public ComputerBuilder keyboard() {
        computer.setKeyboard("HHKB键盘");
        return this;
    }

    @Override
    public ComputerBuilder display() {
        computer.setDisplay("戴尔显示器");
        return this;
    }

    @Override
    public Computer build() {
        return computer;
    }
}
