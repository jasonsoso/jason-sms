package com.jason.sms.repository.cms;

import java.util.List;
import com.jason.framework.orm.Page;
import com.jason.sms.domain.cms.Category;

/**
 * 分类 持久层接口类
 * @author Jason
 * @date 2015-11-13
 */
public interface CategoryRepository {

	Category get(Long id);

	List<Category> query(Object object);

	Page<Category> queryPage(Page<Category> page);

	void update(Category entity);
	
	void delete(Category entity);

	void save(Category entity);
	
	List<Category> queryCategories();
}
