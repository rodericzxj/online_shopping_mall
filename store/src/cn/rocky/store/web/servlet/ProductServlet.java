package cn.rocky.store.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.rocky.store.domain.PageModel;
import cn.rocky.store.domain.Product;
import cn.rocky.store.service.ProductService;
import cn.rocky.store.service.serviceImp.ProductServiceImp;
import cn.rocky.store.web.base.BaseServlet;

public class ProductServlet extends BaseServlet {
	public String findProductByPid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取商品pid
		String pid=request.getParameter("pid");
		//根据商品pid查询商品信息
		ProductService ProductService=new ProductServiceImp();
		Product product=ProductService.findProductByPid(pid);
		//将获取到的商品放入request
		request.setAttribute("product", product);
		//转发到/jsp/product_info.jsp
		return "/jsp/product_info.jsp";
	}
	
	//findProductsByCidWithPage
	public String findProductsByCidWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//获取cid,num
		String cid=request.getParameter("cid");
		int curNum=Integer.parseInt(request.getParameter("num"));
		//调用业务层功能:以分页形式查询当前类别下商品信息
		ProductService ProductService=new ProductServiceImp();
		PageModel pm=ProductService.findProductsByCidWithPage(cid,curNum);
		request.setAttribute("page", pm);
		return  "/jsp/product_list.jsp";
	}
	
}
