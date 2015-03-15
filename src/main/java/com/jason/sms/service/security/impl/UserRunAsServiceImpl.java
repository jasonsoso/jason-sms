package com.jason.sms.service.security.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.sms.repository.security.UserRunAsRepository;
import com.jason.sms.service.security.UserRunAsService;

@Transactional
@Service
public class UserRunAsServiceImpl implements UserRunAsService {
    @Autowired
    private UserRunAsRepository userRunAsRepository;

    @Override
    public void grantRunAs(Long fromUserId, Long toUserId) {
    	userRunAsRepository.grantRunAs(fromUserId, toUserId);
    }

    @Override
    public void revokeRunAs(Long fromUserId, Long toUserId) {
    	userRunAsRepository.revokeRunAs(fromUserId, toUserId);
    }

    @Override
    public boolean exists(Long fromUserId, Long toUserId) {
        return userRunAsRepository.exists(fromUserId, toUserId);
    }

    @Override
    public List<Long> findFromUserIds(Long toUserId) {
        return userRunAsRepository.findFromUserIds(toUserId);
    }

    @Override
    public List<Long> findToUserIds(Long fromUserId) {
        return userRunAsRepository.findToUserIds(fromUserId);
    }
}
