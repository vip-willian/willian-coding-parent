package cn.willian.coding.mybatis.dao;

import cn.willian.coding.mybatis.domain.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_detail
     *
     * @mbg.generated Fri Nov 08 00:15:07 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_detail
     *
     * @mbg.generated Fri Nov 08 00:15:07 CST 2024
     */
    int insert(OrderDetail row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_detail
     *
     * @mbg.generated Fri Nov 08 00:15:07 CST 2024
     */
    int insertSelective(OrderDetail row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_detail
     *
     * @mbg.generated Fri Nov 08 00:15:07 CST 2024
     */
    OrderDetail selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_detail
     *
     * @mbg.generated Fri Nov 08 00:15:07 CST 2024
     */
    int updateByPrimaryKeySelective(OrderDetail row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_detail
     *
     * @mbg.generated Fri Nov 08 00:15:07 CST 2024
     */
    int updateByPrimaryKey(OrderDetail row);
}