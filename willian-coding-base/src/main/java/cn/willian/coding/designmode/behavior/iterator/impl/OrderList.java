package cn.willian.coding.designmode.behavior.iterator.impl;

import java.util.List;

import cn.willian.coding.designmode.behavior.iterator.AbstractIterator;
import cn.willian.coding.designmode.behavior.iterator.AbstractList;
import cn.willian.coding.designmode.behavior.iterator.Iterator;
import cn.willian.coding.designmode.behavior.iterator.bean.Order;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 20:51:51
 */
public class OrderList extends AbstractList<Order> {

    public OrderList(List<Order> data) {
        super(data);
    }

    @Override
    public Iterator<Order> iterator() {
        return new AbstractIterator<Order>(this) {};
    }
}
