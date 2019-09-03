package cn.rocky.store.service;

import cn.rocky.store.domain.Order;
import cn.rocky.store.domain.PageModel;
import cn.rocky.store.domain.User;

public interface OrderService {

	void saveOrder(Order order)throws Exception;

	PageModel findMyOrdersWithPage(User user, int curNum)throws Exception;

	Order findOrderByOid(String oid)throws Exception;

	void updateOrder(Order order)throws Exception;

}
