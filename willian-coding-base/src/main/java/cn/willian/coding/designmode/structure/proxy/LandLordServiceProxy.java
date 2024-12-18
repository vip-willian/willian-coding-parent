package cn.willian.coding.designmode.structure.proxy;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 15:16:26
 */
// 代理类: 中介对象
public class LandLordServiceProxy implements LandLordService {

    private final String intermediary;
    // 被代理的房东对象
    private final LandLordService landLordService;

    // 可暴露可不暴露，不暴露完全由中介对象代理
    public LandLordServiceProxy(String intermediary, LandLordService landLordService) {
        this.intermediary = intermediary;
        this.landLordService = landLordService;
    }

    @Override
    public void receiveMoney(String name, Double amount) {

        System.out.println("中介(" +intermediary +") 找到了 租客(" + name + ")");
        // 进行收租
        landLordService.receiveMoney(name, amount);
        // 支付佣金
        if (landLordService instanceof LandLordServiceImpl) {
            System.out.println("房东(" + ((LandLordServiceImpl)landLordService).getLandlord() + ") 支付佣金给中介("
                + intermediary + ") : " + amount * 0.1);
        }
    }
}
