package cn.willian.coding.designmode.behavior.visitor;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 15:41:40
 */
public class VisitorMain {
    public static void main(String[] args) {

        EmployeeList list = new EmployeeList();
        Employee fte1, fte2, fte3, pte1, pte2;

        // 三位正式员工
        fte1 = new FullTimeEmployee("乔峰", 3200.00, 45);
        fte2 = new FullTimeEmployee("段誉", 2000.00, 40);
        fte3 = new FullTimeEmployee("虚竹", 2400.00, 38);
        // 二位临时员工
        pte1 = new PartTimeEmployee("杨过", 80.00, 20);
        pte2 = new PartTimeEmployee("小龙女", 60.00, 18);

        list.addEmployee(fte1);
        list.addEmployee(fte2);
        list.addEmployee(fte3);
        list.addEmployee(pte1);
        list.addEmployee(pte2);

        System.out.println("------HR部门查看员工工时------");
        Department hr = new HRDepartment();
        list.accept(hr);

        System.out.println("------财政部门统计员工工资------");
        Department fa = new FADepartment();
        list.accept(fa);
    }
}
