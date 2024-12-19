package cn.willian.coding.designmode.behavior.visitor;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 15:31:30
 */
// 人力资源部类：具体访问者类
public class HRDepartment implements Department {

    // 实现人力资源部对全职员工的访问
    @Override
    public void visit(FullTimeEmployee employee) {

        // 查看正式工工时
        int workTime = employee.getWorkTime();
        String name = employee.getName();
        System.out.println("正式员工【" + name + "】实际工作时间为：" + workTime + "小时");
        if (workTime > 40) {
            System.out.println("正式员工【" + name + "】加班时间为：" + (workTime - 40) + "小时");
        } else if (workTime < 40) {
            System.out.println("正式员工【" + name + "】请假时间为：" + (40 - workTime) + "小时");
        }
    }

    // 实现人力资源部对兼职员工的访问
    @Override
    public void visit(PartTimeEmployee employee) {

        // 查看临时工工时
        int workTime = employee.getWorkTime();
        String name = employee.getName();
        System.out.println("临时工【" + name + "】实际工作时间为：" + workTime + "小时");
    }
}
