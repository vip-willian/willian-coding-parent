package cn.willian.coding.designmode.behavior.iterator;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 20:21:46
 */
public interface Iterator<T> {

    /**
     * 移动到上一个元素
     */
    void previous();

    /**
     * 移动到下一个元素
     */
    void next();

    /**
     * 获取上一个元素
     */
    T getPrevious();

    /**
     * 获取下一个元素
     */
    T getNext();

    /**
     * 是否为第一个元素
     */
    boolean isFirst();

    /**
     * 是否为最后一个元素
     */
    boolean isLast();
}
