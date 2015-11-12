package com.jason.sms.service.cms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.framework.orm.Page;
import com.jason.sms.domain.cms.Channel;
import com.jason.sms.repository.cms.ChannelRepository;
import com.jason.sms.service.cms.ChannelService;


/**
 * 频道 业务逻辑实现类
 * @author Jason
 * @date 2015-11-11
 */
@Transactional
@Service
public class ChannelServiceImpl  implements ChannelService {

	@Autowired
	private ChannelRepository channelRepository;

	@Override
	public Channel get(Long id) {
		return channelRepository.get(id);
	}

	@Override
	public List<Channel> query(Object object) {
		return channelRepository.query(object);
	}

	@Override
	public Page<Channel> queryPage(Page<Channel> page) {
		return channelRepository.queryPage(page);
	}

	@Override
	public void update(Channel entity) {
		channelRepository.update(entity);
	}

	@Override
	public void delete(Channel entity) {
		channelRepository.delete(entity);
	}

	@Override
	public void save(Channel entity) {
		channelRepository.save(entity);
	}
	@Override
	public Channel queryUniqueByPath(String path) {
		return channelRepository.queryUniqueByPath(path);
	}

	@Override
	public List<Channel> queryChildById(String id) {
		return channelRepository.queryChildById(id);
	}

	@Override
	public List<Channel> queryChannels() {
		return channelRepository.queryChannels();
	}

	@Override
	public List<Channel> queryTop() {
		return channelRepository.queryTop();
	}
	
	
}
