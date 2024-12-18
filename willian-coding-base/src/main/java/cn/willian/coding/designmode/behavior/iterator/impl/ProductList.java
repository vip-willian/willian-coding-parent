package cn.willian.coding.designmode.behavior.iterator.impl;

import java.util.List;

import cn.willian.coding.designmode.behavior.iterator.AbstractIterator;
import cn.willian.coding.designmode.behavior.iterator.AbstractList;
import cn.willian.coding.designmode.behavior.iterator.Iterator;
import cn.willian.coding.designmode.behavior.iterator.bean.Product;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 20:51:51
 */
public class ProductList extends AbstractList<Product> {

    public ProductList(List<Product> data) {
        super(data);
    }

    @Override
    public Iterator<Product> iterator() {
        return new AbstractIterator<Product>(this) {};
    }
}
