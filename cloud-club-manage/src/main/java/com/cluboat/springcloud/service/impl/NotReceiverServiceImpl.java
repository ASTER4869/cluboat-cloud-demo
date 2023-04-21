package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.DTO.NotificationDTO;
import com.cluboat.springcloud.entity.NotReceiverEntity;
import com.cluboat.springcloud.entity.NotificationEntity;
import com.cluboat.springcloud.entity.param.GetNotificationParam;
import com.cluboat.springcloud.mapper.NotReceiverMapper;
import com.cluboat.springcloud.mapper.NotificationMapper;
import com.cluboat.springcloud.service.NotReceiverService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NotReceiverServiceImpl extends ServiceImpl<NotReceiverMapper, NotReceiverEntity> implements NotReceiverService {

    @Resource
    NotReceiverMapper notReceiverMapper;

    @Override
    public List<NotificationDTO> testMPJ(){
        List<NotificationDTO> testList = notReceiverMapper.selectJoinList(NotificationDTO.class, new MPJLambdaWrapper<NotReceiverEntity>()
                .selectAll(NotificationEntity.class)
                .leftJoin(NotificationEntity.class, NotificationEntity::getNotificationId, NotReceiverEntity::getNotificationId)
                .eq(NotificationEntity::getSendAdminId, 1)
        );
        return testList;
    }
}
