package com.cluboat.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cluboat.springcloud.entity.NotReceiverEntity;
import com.cluboat.springcloud.entity.dto.NotificationDTO;
import com.cluboat.springcloud.entity.param.GetNotificationParam;

import java.util.List;

public interface NotReceiverService extends IService<NotReceiverEntity> {
    public List<NotificationDTO> GetSendNotification(GetNotificationParam notificationParam);
}
