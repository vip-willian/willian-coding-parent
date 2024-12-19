package cn.willian.coding.designmode.behavior.visitor;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 15:24:47
 */
// 部门类：抽象访问者类
public interface Department {

    /**
     * 访问全职员工
     */
    void visit(FullTimeEmployee employee);

    /**
     * 访问兼职员工
     */
    void visit(PartTimeEmployee employee);
}
