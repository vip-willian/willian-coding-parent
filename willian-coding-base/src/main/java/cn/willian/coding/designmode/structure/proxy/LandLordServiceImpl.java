package cn.willian.coding.designmode.structure.proxy;

import lombok.Getter;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 15:14:12
 */
public class LandLordServiceImpl implements LandLordService {

    @Getter
    private final String landlord;

    public LandLordServiceImpl(String landlord) {
        this.landlord = landlord;
    }

    @Override
    public void receiveMoney(String name, Double amount) {

        System.out.println("房东(" +landlord+ ")对租客(" + name + ")收租: " + amount + "元");
    }
}
