package com.jason.sms.repository.cms;

import java.util.List;
import com.jason.framework.orm.Page;
import com.jason.sms.domain.cms.Article;

/**
 * 文章 持久层接口类
 * @author Jason
 * @date 2015-11-13
 */
public interface ArticleRepository {

	Article get(Long id);

	List<Article> query(Object object);

	Page<Article> queryPage(Page<Article> page);

	void update(Article entity);
	
	void delete(Article entity);

	void save(Article entity);
}
