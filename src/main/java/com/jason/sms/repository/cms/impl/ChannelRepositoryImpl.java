package com.jason.sms.repository.cms.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jason.framework.orm.mybatis.MybatisRepositorySupport;
import com.jason.sms.repository.cms.ChannelRepository;
import com.jason.sms.domain.cms.Channel;

/**
 * 频道 持久层实现类
 * @author Jason
 * @date 2015-11-11
 */
@Repository
public class ChannelRepositoryImpl extends MybatisRepositorySupport<Long, Channel> implements ChannelRepository {

	@Override
	protected String getNamespace() {
		return "com.jason.sms.domain.cms.Channel";
	}

	@Override
	public Channel queryUniqueByPath(String path) {
		return (Channel) getSqlSession().selectOne(getNamespace() + ".queryUniqueByPath", path);
	}

	@Override
	public List<Channel> queryTop() {
		return getSqlSession().selectList(getNamespace() + ".queryTop");
	}

	@Override
	public List<Channel> queryChildById(String id) {
		return getSqlSession().selectList(getNamespace() + ".queryChildById",id);
	}

	@Override
	public List<Channel> queryChannels() {
		return getSqlSession().selectList(getNamespace()+".queryChannels");
	}
}
