package com.cluboat.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cluboat.springcloud.entity.NotificationEntity;
import com.cluboat.springcloud.entity.dto.NotificationDTO;

import java.util.List;

public interface NotificationService extends IService<NotificationEntity> {
    public List<NotificationDTO> GetMyNotification(Integer userId);
    public List<NotificationDTO> GetAllNotification();


}
