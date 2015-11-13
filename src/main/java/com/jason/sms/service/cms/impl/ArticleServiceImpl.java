package com.jason.sms.service.cms.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.framework.orm.Page;
import com.jason.sms.domain.cms.Article;
import com.jason.sms.repository.cms.ArticleRepository;
import com.jason.sms.service.cms.ArticleService;
import com.jason.sms.util.shiro.ShiroUserUtils;


/**
 * 文章 业务逻辑实现类
 * @author Jason
 * @date 2015-11-13
 */
@Transactional
@Service
public class ArticleServiceImpl  implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public Article get(Long id) {
		return articleRepository.get(id);
	}

	@Override
	public List<Article> query(Object object) {
		return articleRepository.query(object);
	}

	@Override
	public Page<Article> queryPage(Page<Article> page) {
		return articleRepository.queryPage(page);
	}

	@Override
	public void update(Article entity) {
		long userId = ShiroUserUtils.getCurrentUserId();
		Date now = new Date();
		
		entity.setUpdateId(userId);
		entity.setUpdateTime(now);
		
		articleRepository.update(entity);
	}

	@Override
	public void delete(Article entity) {
		articleRepository.delete(entity);
	}

	@Override
	public void save(Article entity) {
		long userId = ShiroUserUtils.getCurrentUserId();
		Date now = new Date();
		
		entity.setCreateId(userId);
		entity.setCreateTime(now);
		entity.setUpdateId(userId);
		entity.setUpdateTime(now);
		
		entity.setStatus(0);
		
		articleRepository.save(entity);
	}
	
	
}
