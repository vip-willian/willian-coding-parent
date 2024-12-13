package cn.willian.coding.designmode.created.builder;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-13 11:20:11
 */
public class BuilderMain {
    public static void main(String[] args) {
        // 苹果电脑厂商
        ComputerManufacturer macManufacturer = new ComputerManufacturer(new MacComputerBuilder());
        Computer macComputer = macManufacturer.build("8核", "32G", "512T");
        System.out.println(macComputer);

        // 戴尔电脑厂商
        ComputerManufacturer dellManufacturer = new ComputerManufacturer(new DellComputerBuilder());
        Computer dellComputer = dellManufacturer.build("4核", "16G", "10T");
        System.out.println(dellComputer);
    }
}
