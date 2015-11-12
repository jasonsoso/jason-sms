package com.jason.sms.service.cms.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.framework.orm.Page;
import com.jason.framework.util.FileUtils;
import com.jason.sms.domain.cms.Template;
import com.jason.sms.repository.cms.TemplateRepository;
import com.jason.sms.service.cms.TemplateService;
import com.jason.sms.util.Conf;
import com.jason.sms.util.ConfigureOnWeb;
import com.jason.sms.util.FilesHelper;


/**
 * 模板 业务逻辑实现类
 * @author Jason
 * @date 2015-11-11
 */
@Transactional
@Service
public class TemplateServiceImpl  implements TemplateService {

	@Autowired
	private TemplateRepository templateRepository;
	@Autowired
	private ConfigureOnWeb confOnWeb;
	
	@Override
	public Template get(Long id) {
		return templateRepository.get(id);
	}

	@Override
	public List<Template> query(Object object) {
		return templateRepository.query(object);
	}

	@Override
	public Page<Template> queryPage(Page<Template> page) {
		return templateRepository.queryPage(page);
	}

	@Override
	public void update(Template entity) {
		entity.setUpdateTime(new Date());
		templateRepository.update(entity);
	}

	@Override
	public void delete(Template entity) {
		templateRepository.delete(entity);
	}

	@Override
	public void save(Template entity) {
		entity.setUpdateTime(new Date());
		templateRepository.save(entity);
	}

	@Override
	public void sync(Template entity) {
		Conf conf = confOnWeb.wrap(Conf.defaultOne());
		String path = FilesHelper.join(conf.getRootPath(), entity.getPhysicalUrl());
		//删除源文件，新建新文件
		FileUtils.deleteFile(path);
		FileUtils.writeFile(entity.getContent(), path);
	}

	@Override
	public List<Template> queryTemplates() {
		return templateRepository.queryTemplates();
	}
	
	
}
