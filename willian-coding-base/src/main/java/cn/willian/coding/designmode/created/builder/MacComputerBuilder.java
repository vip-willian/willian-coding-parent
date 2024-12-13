package cn.willian.coding.designmode.created.builder;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-13 11:23:52
 */
public class MacComputerBuilder extends ComputerBuilder {

    @Override
    public ComputerBuilder cpu(String core) {
        ComputerCpu macCpu = new ComputerCpu();
        macCpu.setCore(core);
        macCpu.setBrand("苹果");
        computer.setCpu(macCpu);
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
        macHardDisk.setType("SSD");
        macHardDisk.setCapacity("512T");
        computer.setHardDisk(macHardDisk);
        return this;
    }

    @Override
    public ComputerBuilder mouse() {
        computer.setMouse("苹果鼠标");
        return this;
    }

    @Override
    public ComputerBuilder keyboard() {
        computer.setKeyboard("苹果键盘");
        return this;
    }

    @Override
    public ComputerBuilder display() {
        computer.setDisplay("苹果显示器");
        return this;
    }

    @Override
    public Computer build() {
        return computer;
    }
}
