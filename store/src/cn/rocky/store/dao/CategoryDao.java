package cn.rocky.store.dao;

import java.util.List;

import cn.rocky.store.domain.Category;

public interface CategoryDao {

	List<Category> getAllCats()throws Exception;

}
