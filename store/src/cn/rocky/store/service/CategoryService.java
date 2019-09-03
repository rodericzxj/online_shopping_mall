package cn.rocky.store.service;

import java.util.List;

import cn.rocky.store.domain.Category;

public interface CategoryService {

	List<Category> getAllCats()throws Exception;

}
