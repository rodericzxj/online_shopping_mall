package cn.rocky.store.dao.daoImp;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.rocky.store.dao.CategoryDao;
import cn.rocky.store.domain.Category;
import cn.rocky.store.utils.JDBCUtils;

public class CategoryDaoImp implements CategoryDao {

	@Override
	public List<Category> getAllCats() throws Exception {
		String sql="select * from category";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Category>(Category.class));
		
	}

}
