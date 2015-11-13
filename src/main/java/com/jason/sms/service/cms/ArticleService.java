package com.jason.sms.service.cms;

import java.util.List;

import com.jason.framework.orm.Page;
import com.jason.sms.domain.cms.Article;

/**
 * 文章 业务逻辑接口类
 * @author Jason
 * @date 2015-11-13
 */
public interface ArticleService {

	void update(Article entity);

	void delete(Article entity);

	void save(Article entity);
	
	Article get(Long id);

	List<Article> query(Object object);

	Page<Article> queryPage(Page<Article> page);
}
