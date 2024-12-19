package cn.willian.coding.designmode.behavior.visitor;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 15:31:30
 */
// 财务部类：具体访问者类
public class FADepartment implements Department {

    // 实现财务部对全职员工的访问
    @Override
    public void visit(FullTimeEmployee employee) {

        String name = employee.getName();
        int workTime = employee.getWorkTime();
        double weekWage = employee.getWeeklyWage();
        // 如果工时超过40个小时，超过部分属于加班费,按每小时100
        if (workTime > 40) {
            weekWage = weekWage + (workTime - 40) * 100;
        } else if (workTime < 40) {
            // 如果工时少于40个小时，所缺时间按请假处理,请假所扣工资以80元/小时计算，直到基本工资扣除到零为止
            weekWage = weekWage - (40 - workTime) * 80;
            if (weekWage < 0) {
                weekWage = 0;
            }
        }
        System.out.println("正式员工【" + name + "】实际工资为：" + weekWage + "元");
    }

    // 实现财务部对兼职员工的访问
    @Override
    public void visit(PartTimeEmployee employee) {

        String name = employee.getName();
        int workTime = employee.getWorkTime();
        double hourWage = employee.getHourWage();
        // 基本工资按小时计算
        System.out.println("临时工【" + name + "】实际工资为：" + workTime * hourWage + "元");
    }
}
