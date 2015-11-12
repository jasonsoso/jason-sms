package com.jason.sms.repository.cms;

import java.util.List;
import com.jason.framework.orm.Page;
import com.jason.sms.domain.cms.Channel;
import com.jason.sms.domain.cms.Template;

/**
 * 模板 持久层接口类
 * @author Jason
 * @date 2015-11-11
 */
public interface TemplateRepository {

	Template get(Long id);

	List<Template> query(Object object);

	Page<Template> queryPage(Page<Template> page);

	void update(Template entity);
	
	void delete(Template entity);

	void save(Template entity);
	
	List<Template> queryTemplates();

}
