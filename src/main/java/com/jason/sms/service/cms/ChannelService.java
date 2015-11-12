package com.jason.sms.service.cms;

import java.util.List;

import com.jason.framework.orm.Page;
import com.jason.sms.domain.cms.Channel;

/**
 * 频道 业务逻辑接口类
 * @author Jason
 * @date 2015-11-11
 */
public interface ChannelService {

	void update(Channel entity);

	void delete(Channel entity);

	void save(Channel entity);
	
	Channel get(Long id);

	List<Channel> query(Object object);

	Page<Channel> queryPage(Page<Channel> page);
	
	Channel queryUniqueByPath(String path);

	List<Channel> queryChildById(String id);
	
	List<Channel> queryChannels();
	
	List<Channel> queryTop();
}
