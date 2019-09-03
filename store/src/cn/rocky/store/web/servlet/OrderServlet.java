package cn.rocky.store.web.servlet;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.rocky.store.domain.Cart;
import cn.rocky.store.domain.CartItem;
import cn.rocky.store.domain.Order;
import cn.rocky.store.domain.OrderItem;
import cn.rocky.store.domain.PageModel;
import cn.rocky.store.domain.User;
import cn.rocky.store.service.OrderService;
import cn.rocky.store.service.serviceImp.OrderServiceImp;
import cn.rocky.store.utils.UUIDUtils;
import cn.rocky.store.web.base.BaseServlet;

public class OrderServlet extends BaseServlet {
	// saveOrder  将购物车中的信息以订单的形式保存
	public String saveOrder(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//确认用户登录状态
		User user=(User)req.getSession().getAttribute("loginUser");
		if(null==user){
			req.setAttribute("msg", "请登录之后在下单");
			return "/jsp/info.jsp";
		}
		//获取购物车
		Cart cart=(Cart)req.getSession().getAttribute("cart");
		//创建订单对象,为订单对象赋值
		Order order=new Order();
		order.setOid(UUIDUtils.getCode());
		order.setOrdertime(new Date());
		order.setTotal(cart.getTotal());
		order.setState(1);
		order.setUser(user);
		//遍历购物项的同时,创建订单项,为订单项赋值
		for (CartItem item : cart.getCartItems()) {
			OrderItem orderItem=new OrderItem();
			orderItem.setItemid(UUIDUtils.getCode());
			orderItem.setQuantity(item.getNum());
			orderItem.setTotal(item.getSubTotal());
			orderItem.setProduct(item.getProduct());
			
			//设置当前的订单项属于哪个订单:程序的角度体检订单项和订单对应关系
			orderItem.setOrder(order);
			order.getList().add(orderItem);
		}
		//调用业务层功能:保存订单
		OrderService OrderService=new OrderServiceImp();
		//将订单数据,用户的数据,订单下所有的订单项都传递到了service层
		OrderService.saveOrder(order);
		//清空购物车
		cart.clearCart();
		//将订单放入request
		req.setAttribute("order", order);
		//转发/jsp/order_info.jsp
		return "/jsp/order_info.jsp";
	}
	
	//findMyOrdersWithPage
	public String findMyOrdersWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		User user=(User)req.getSession().getAttribute("loginUser");
		int curNum=Integer.parseInt(req.getParameter("num"));
		OrderService OrderService=new OrderServiceImp();
		PageModel pm=OrderService.findMyOrdersWithPage(user,curNum);
	
		req.setAttribute("page", pm);
		
		return "/jsp/order_list.jsp";
		
	}
	
	//findOrderByOid
	public String findOrderByOid(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取到订单oid
		String oid=req.getParameter("oid");
		//调用业务层功能:根据订单编号查询订单信息
		OrderService OrderService=new OrderServiceImp();
		Order order=OrderService.findOrderByOid(oid);
		req.setAttribute("order", order);
		return "/jsp/order_info.jsp";
	}
	
	
	
	
}
