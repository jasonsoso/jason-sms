package com.jason.sms.service.security;

import java.util.List;

public interface UserRunAsService {

    public void grantRunAs(Long fromUserId, Long toUserId);
    public void revokeRunAs(Long fromUserId, Long toUserId);

    public boolean exists(Long fromUserId, Long toUserId);

    public List<Long> findFromUserIds(Long toUserId);
    public List<Long> findToUserIds(Long fromUserId);

}
