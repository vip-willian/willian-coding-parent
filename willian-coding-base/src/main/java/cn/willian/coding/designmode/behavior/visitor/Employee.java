package cn.willian.coding.designmode.behavior.visitor;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 15:23:52
 */
// 员工类：抽象元素类
public interface Employee {

    void accept(Department department);
}
