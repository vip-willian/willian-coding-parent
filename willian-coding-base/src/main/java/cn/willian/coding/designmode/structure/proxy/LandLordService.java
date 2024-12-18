package cn.willian.coding.designmode.structure.proxy;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 15:11:34
 */
public interface LandLordService {

    /**
     * 收租
     *
     * @param name 租客姓名
     * @param amount 租金
     */
    void receiveMoney(String name,Double amount);
}
