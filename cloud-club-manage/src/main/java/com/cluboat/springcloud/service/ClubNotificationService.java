package com.cluboat.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cluboat.springcloud.entity.DTO.NotificationDTO;
import com.cluboat.springcloud.entity.NotificationEntity;

import java.util.List;

public interface ClubNotificationService extends IService<NotificationEntity> {
    public List<NotificationDTO> GetNotificationBySendUserId(Integer id);

}