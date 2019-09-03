package cn.rocky.store.service.serviceImp;

import java.util.List;

import cn.rocky.store.dao.ProductDao;
import cn.rocky.store.dao.daoImp.ProductDaoImp;
import cn.rocky.store.domain.PageModel;
import cn.rocky.store.domain.Product;
import cn.rocky.store.service.ProductService;

public class ProductServiceImp implements ProductService {

	ProductDao ProductDao=new ProductDaoImp();
	
	@Override
	public Product findProductByPid(String pid) throws Exception {
		return ProductDao.findProductByPid(pid);
		
	}

	@Override
	public List<Product> findHots() throws Exception {
		return ProductDao.findHots();
	}

	@Override
	public List<Product> findNews() throws Exception {
		return ProductDao.findNews();
	}

	@Override
	public PageModel findProductsByCidWithPage(String cid, int curNum) throws Exception {
		//1_创建PageModel对象 目的:计算分页参数
		//统计当前分类下商品个数  select count(*) from product where cid=?
		int totalRecords=ProductDao.findTotalRecords(cid);
		PageModel pm=new PageModel(curNum,totalRecords,12);
		//2_关联集合 select * from product where cid =? limit ? ,?
		List list=ProductDao.findProductsByCidWithPage(cid,pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//3_关联url
		pm.setUrl("ProductServlet?method=findProductsByCidWithPage&cid="+cid);
		return pm;
	}

}
