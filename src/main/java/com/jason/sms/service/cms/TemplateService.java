package com.jason.sms.service.cms;

import java.util.List;

import com.jason.framework.orm.Page;
import com.jason.sms.domain.cms.Template;

/**
 * 模板 业务逻辑接口类
 * @author Jason
 * @date 2015-11-11
 */
public interface TemplateService {

	void update(Template entity);

	void delete(Template entity);

	void save(Template entity);
	
	Template get(Long id);

	List<Template> query(Object object);

	Page<Template> queryPage(Page<Template> page);
	
	/**
	 * 同步文件
	 * @param entity
	 */
	void sync(Template entity);

	List<Template> queryTemplates();
}
