package cn.rocky.store.service.serviceImp;

import java.util.List;

import cn.rocky.store.dao.CategoryDao;
import cn.rocky.store.dao.daoImp.CategoryDaoImp;
import cn.rocky.store.domain.Category;
import cn.rocky.store.service.CategoryService;

public class CategoryServiceImp implements CategoryService {

	@Override
	public List<Category> getAllCats() throws Exception {
		CategoryDao CategoryDao=new CategoryDaoImp();
		return CategoryDao.getAllCats();
	}

}
