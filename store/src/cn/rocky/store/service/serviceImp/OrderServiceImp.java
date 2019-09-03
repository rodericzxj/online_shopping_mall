package cn.rocky.store.service.serviceImp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.rocky.store.dao.daoImp.OrderDaoImp;
import cn.rocky.store.domain.Order;
import cn.rocky.store.domain.OrderItem;
import cn.rocky.store.domain.PageModel;
import cn.rocky.store.domain.User;
import cn.rocky.store.service.OrderService;
import cn.rocky.store.utils.JDBCUtils;

public class OrderServiceImp implements OrderService {

	OrderDao orderDao = new OrderDaoImp();

	@Override
	public void saveOrder(Order order) throws SQLException {

		Connection conn = null;
		try {

			conn = JDBCUtils.getConnection();

			conn.setAutoCommit(false);

			orderDao.saveOrder(conn, order);

			for (OrderItem item : order.getList()) {
				orderDao.saveOrderItem(conn, item);
			}

			conn.commit();
		} catch (Exception e) {

			conn.rollback();
		}
	}

	@Override
	public PageModel findMyOrdersWithPage(User user, int curNum) throws Exception {

		int totalRecords = orderDao.getTotalRecords(user);
		PageModel pm = new PageModel(curNum, totalRecords, 3);

		List list = orderDao.findMyOrdersWithPage(user, pm.getStartIndex(), pm.getPageSize());
		pm.setList(list);

		pm.setUrl("OrderServlet?method=findMyOrdersWithPage");
		return pm;
	}

	@Override
	public Order findOrderByOid(String oid) throws Exception {
		return orderDao.findOrderByOid(oid);

	}

	@Override
	public void updateOrder(Order order) throws Exception {
		orderDao.updateOrder(order);

	}
}