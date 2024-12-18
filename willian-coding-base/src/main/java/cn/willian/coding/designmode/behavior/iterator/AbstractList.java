package cn.willian.coding.designmode.behavior.iterator;

import java.util.List;

import lombok.Getter;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 20:30:04
 */
@Getter
public abstract class AbstractList<T> {

    protected List<T> data;

    public AbstractList(List<T> data) {
        this.data = data;
    }

    public void add(T t) {
        data.add(t);
    }

    public void remove(T t) {
        data.remove(t);
    }

    /**
     * 创建迭代器
     */
    public abstract Iterator<T> iterator();
}
