package com.jason.sms.repository.security.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jason.framework.orm.mybatis.MybatisRepositorySupport;
import com.jason.sms.domain.security.UserRunAs;
import com.jason.sms.repository.security.UserRunAsRepository;

/**
 * <p>UserRunAs: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Repository
public class MybatisUserRunAsRepository  extends MybatisRepositorySupport<Long, UserRunAs> implements UserRunAsRepository {

    @Override
    public void grantRunAs(Long fromUserId, Long toUserId) {
        if(!exists(fromUserId, toUserId)) {
        	UserRunAs entity = new UserRunAs();
        	entity.setFromUserId(fromUserId);
        	entity.setToUserId(toUserId);
            super.save(entity);
        }
    }

    public boolean exists(Long fromUserId, Long toUserId) {
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("fromUserId", fromUserId);
    	map.put("toUserId", toUserId);
    	long count = super.getSqlSession().selectOne(getNamespace()+".exists", map);
        return count != 0;
    }

    @Override
    public void revokeRunAs(Long fromUserId, Long toUserId) {
    	UserRunAs entity = new UserRunAs();
    	entity.setFromUserId(fromUserId);
    	entity.setToUserId(toUserId);
    	super.delete(entity);
    }

    @Override
    public List<Long> findFromUserIds(Long toUserId) {
    	List<Long> list = getSqlSession().selectList(getNamespace()+".findFromUserIds", toUserId);
        return list;
    }

    @Override
    public List<Long> findToUserIds(Long fromUserId) {
        List<Long> list = getSqlSession().selectList(getNamespace()+".findToUserIds", fromUserId);
        return list;
        
    }

	@Override
	protected String getNamespace() {
		return "com.jason.sms.domain.security.UserRunAs";
	}
}
