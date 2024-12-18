package cn.willian.coding.designmode.behavior.iterator;

import java.util.List;

/**
 * 抽象迭代器
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 20:26:23
 */
public abstract class AbstractIterator<T> implements Iterator<T> {

    private final List<T> data;
    // 正向遍历游标
    private int ascIndex;
    // 逆向遍历游标
    private int descIndex;

    public AbstractIterator(AbstractList<T> list) {
        this.data = list.getData();
        this.ascIndex = 0;
        this.descIndex = data.size() - 1;
    }

    @Override
    public void previous() {
        if (descIndex > -1) {
            descIndex--;
        }
    }

    @Override
    public void next() {
        if (ascIndex < data.size()) {
            ascIndex++;
        }
    }

    @Override
    public T getPrevious() {
        return data.get(descIndex);
    }

    @Override
    public T getNext() {
        return data.get(ascIndex);
    }

    @Override
    public boolean isFirst() {
        return descIndex == -1;
    }

    @Override
    public boolean isLast() {
        return (ascIndex == data.size());
    }
}
