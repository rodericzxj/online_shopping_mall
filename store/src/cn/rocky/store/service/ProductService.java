package cn.rocky.store.service;

import java.util.List;

import cn.rocky.store.domain.PageModel;
import cn.rocky.store.domain.Product;

public interface ProductService {

	List<Product> findHots()throws Exception;

	List<Product> findNews()throws Exception;

	Product findProductByPid(String pid)throws Exception;

	PageModel findProductsByCidWithPage(String cid, int curNum)throws Exception;

}
