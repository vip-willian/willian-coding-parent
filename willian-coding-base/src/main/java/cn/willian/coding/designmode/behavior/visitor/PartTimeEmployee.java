package cn.willian.coding.designmode.behavior.visitor;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 每周工作时间不固定，基本工资按小时计算 <br>
 * 不同岗位的临时工小时工资不同。人力资源部只需记录实际工作时间。
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 15:28:23
 */
// 临时员工类：具体元素类
@Data
@AllArgsConstructor
public class PartTimeEmployee implements Employee {

    /**
     * 员工姓名
     */
    private String name;
    /**
     * 每小时基本工资
     */
    private double hourWage;
    /**
     * 工作时长
     */
    private int workTime;

    @Override
    public void accept(Department department) {
        department.visit(this);
    }
}
