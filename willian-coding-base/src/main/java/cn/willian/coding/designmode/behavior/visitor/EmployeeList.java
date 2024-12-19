package cn.willian.coding.designmode.behavior.visitor;

import java.util.ArrayList;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 15:39:47
 */
// 员工列表类：对象结构

public class EmployeeList {

    // 定义一个集合用于存储员工对象
    private final ArrayList<Employee> list = new ArrayList<>();

    public void addEmployee(Employee employee) {
        list.add(employee);
    }

    // 遍历访问员工集合中的每一个员工对象
    public void accept(Department handler) {
        for (Employee employee : list) {
            employee.accept(handler);
        }
    }
}
