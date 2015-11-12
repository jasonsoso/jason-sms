package com.jason.sms.repository.cms;

import java.util.List;
import com.jason.framework.orm.Page;
import com.jason.sms.domain.cms.Channel;

/**
 * 频道 持久层接口类
 * @author Jason
 * @date 2015-11-11
 */
public interface ChannelRepository {

	Channel get(Long id);

	List<Channel> query(Object object);

	Page<Channel> queryPage(Page<Channel> page);

	void update(Channel entity);
	
	void delete(Channel entity);

	void save(Channel entity);
	
	
	Channel queryUniqueByPath(String path);
	
	List<Channel> queryTop();
	
	List<Channel> queryChildById(String id);
	
	List<Channel> queryChannels();
}
