package cn.willian.coding.designmode.created.builder;

import lombok.Data;

/**
 * 电脑硬盘
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-13 11:20:34
 */
@Data
public class ComputerHardDisk {

    /**
     * 类型 机械硬盘（HDD）/ 固态硬盘(SSD）/ 混合硬盘（SSHD）
     */
    private String type;
    /**
     * 容量
     */
    private String capacity;
}
