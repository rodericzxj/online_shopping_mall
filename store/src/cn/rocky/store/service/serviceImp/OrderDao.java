package cn.rocky.store.service.serviceImp;

import java.sql.Connection;
import java.util.List;

import cn.rocky.store.domain.Order;
import cn.rocky.store.domain.OrderItem;
import cn.rocky.store.domain.User;

public interface OrderDao {

	void saveOrder(Connection conn, Order order)throws Exception;

	void saveOrderItem(Connection conn, OrderItem item)throws Exception;

	int getTotalRecords(User user)throws Exception;

	List findMyOrdersWithPage(User user, int startIndex, int pageSize)throws Exception;

	Order findOrderByOid(String oid)throws Exception;

	void updateOrder(Order order)throws Exception;

}
