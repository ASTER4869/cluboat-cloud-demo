package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.DTO.NotificationDTO;
import com.cluboat.springcloud.entity.NotificationEntity;
import com.cluboat.springcloud.mapper.ClubNotificationMapper;
import com.cluboat.springcloud.service.ClubNotificationService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClubNotificationServiceImpl extends ServiceImpl<ClubNotificationMapper, NotificationEntity> implements ClubNotificationService {
    @Resource
    ClubNotificationMapper clubNotificationMapper;

    @Override
    public List<NotificationDTO> GetNotificationBySendUserId(Integer id) {
        List<NotificationDTO> notificationDTOList = clubNotificationMapper.selectJoinList(NotificationDTO.class,
                new MPJLambdaWrapper<NotificationEntity>()
                        .selectAll(NotificationEntity.class)
                        .eq(NotificationEntity::getSendUserId,id));
        return notificationDTOList;
    }
}
