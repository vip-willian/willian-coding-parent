package cn.willian.coding.designmode.behavior.visitor;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <br>
 * 每周工作时间为40小时，不同级别、不同部门的员工每周基本工资不同；如果超过40小时，超出部分按照100元/小时作为加班费。 <br>
 * 如果少于40小时，所缺时间按照请假处理，请假所扣工资以80元/小时计算，直到基本工资扣除到零为止。 <br>
 * 除了记录实际工作时间外，人力资源部需记录加班时长或请假时长，作为员工平时表现的一项依据。
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 15:26:48
 */
// 正式员工类：具体元素类
@Data
@AllArgsConstructor
public class FullTimeEmployee implements Employee {

    /**
     * 员工姓名
     */
    private String name;
    /**
     * 每周基本工资
     */
    private double weeklyWage;
    /**
     * 工作时长
     */
    private int workTime;

    @Override
    public void accept(Department department) {
        department.visit(this);
    }
}
