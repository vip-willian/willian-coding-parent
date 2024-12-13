package cn.willian.coding.designmode.created.builder;

import lombok.Data;

/**
 * 电脑
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-13 11:20:34
 */
@Data
public class Computer {

    /**
     * cpu
     */
    private ComputerCpu cpu;
    /**
     * 内存
     */
    private String memory;
    /**
     * 硬盘
     */
    private ComputerHardDisk hardDisk;
    /**
     * 鼠标
     */
    private String mouse;
    /**
     * 键盘
     */
    private String keyboard;
    /**
     * 显示器
     */
    private String display;
}
