package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.NotificationEntity;
import com.cluboat.springcloud.mapper.ClubNotificationMapper;
import com.cluboat.springcloud.service.ClubNotificationService;
import org.springframework.stereotype.Service;

@Service
public class ClubNotificationServiceImpl extends ServiceImpl<ClubNotificationMapper, NotificationEntity> implements ClubNotificationService {
}
