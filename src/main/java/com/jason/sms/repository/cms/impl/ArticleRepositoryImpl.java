package com.jason.sms.repository.cms.impl;

import org.springframework.stereotype.Repository;

import com.jason.framework.orm.mybatis.MybatisRepositorySupport;
import com.jason.sms.repository.cms.ArticleRepository;
import com.jason.sms.domain.cms.Article;

/**
 * 文章 持久层实现类
 * @author Jason
 * @date 2015-11-13
 */
@Repository
public class ArticleRepositoryImpl extends MybatisRepositorySupport<Long, Article> implements ArticleRepository {

	@Override
	protected String getNamespace() {
		return "com.jason.sms.domain.cms.Article";
	}
}
