package com.jason.sms.repository.cms.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jason.framework.orm.mybatis.MybatisRepositorySupport;
import com.jason.sms.repository.cms.CategoryRepository;
import com.jason.sms.domain.cms.Category;

/**
 * 分类 持久层实现类
 * @author Jason
 * @date 2015-11-13
 */
@Repository
public class CategoryRepositoryImpl extends MybatisRepositorySupport<Long, Category> implements CategoryRepository {

	@Override
	protected String getNamespace() {
		return "com.jason.sms.domain.cms.Category";
	}

	/* (non-Javadoc)
	 * @see com.jason.sms.repository.cms.CategoryRepository#queryCategories()
	 */
	@Override
	public List<Category> queryCategories() {
		return getSqlSession().selectList(getNamespace()+".queryCategories");
	}
}
