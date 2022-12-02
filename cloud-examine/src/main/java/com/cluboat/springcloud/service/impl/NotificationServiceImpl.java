package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.NotificationEntity;
import com.cluboat.springcloud.mapper.NotificationMapper;
import com.cluboat.springcloud.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, NotificationEntity> implements NotificationService {
}
