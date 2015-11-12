package com.jason.sms.repository.cms.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jason.framework.orm.mybatis.MybatisRepositorySupport;
import com.jason.sms.repository.cms.TemplateRepository;
import com.jason.sms.domain.cms.Template;

/**
 * 模板 持久层实现类
 * @author Jason
 * @date 2015-11-11
 */
@Repository
public class TemplateRepositoryImpl extends MybatisRepositorySupport<Long, Template> implements TemplateRepository {

	@Override
	protected String getNamespace() {
		return "com.jason.sms.domain.cms.Template";
	}
	@Override
	public List<Template> queryTemplates()
	{
		return getSqlSession().selectList(getNamespace()+".queryTemplates");
	}
}
