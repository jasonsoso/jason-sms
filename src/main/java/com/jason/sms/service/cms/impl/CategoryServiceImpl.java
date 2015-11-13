package com.jason.sms.service.cms.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.framework.orm.Page;
import com.jason.sms.domain.cms.Category;
import com.jason.sms.repository.cms.CategoryRepository;
import com.jason.sms.service.cms.CategoryService;
import com.jason.sms.util.shiro.ShiroUserUtils;


/**
 * 分类 业务逻辑实现类
 * @author Jason
 * @date 2015-11-13
 */
@Transactional
@Service
public class CategoryServiceImpl  implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category get(Long id) {
		return categoryRepository.get(id);
	}

	@Override
	public List<Category> query(Object object) {
		return categoryRepository.query(object);
	}

	@Override
	public Page<Category> queryPage(Page<Category> page) {
		return categoryRepository.queryPage(page);
	}

	@Override
	public void update(Category entity) {
		long userId = ShiroUserUtils.getCurrentUserId();
		Date now = new Date();
		
		entity.setUpdateId(userId);
		entity.setUpdateTime(now);
		
		categoryRepository.update(entity);
	}

	@Override
	public void delete(Category entity) {
		categoryRepository.delete(entity);
	}

	@Override
	public void save(Category entity) {
		long userId = ShiroUserUtils.getCurrentUserId();
		Date now = new Date();
		
		entity.setCreateId(userId);
		entity.setCreateTime(now);
		entity.setUpdateId(userId);
		entity.setUpdateTime(now);
		
		categoryRepository.save(entity);
	}

	/* (non-Javadoc)
	 * @see com.jason.sms.service.cms.CategoryService#queryCategories()
	 */
	@Override
	public List<Category> queryCategories() {
		return categoryRepository.queryCategories();
	}
	
	
}
