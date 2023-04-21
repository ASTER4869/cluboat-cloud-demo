package com.cluboat.springcloud.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.NotReceiverEntity;
import com.cluboat.springcloud.entity.NotificationEntity;
import com.cluboat.springcloud.entity.dto.NotificationDTO;
import com.cluboat.springcloud.mapper.NotificationMapper;
import com.cluboat.springcloud.service.NotificationService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NotificationServiceImpl  extends ServiceImpl<NotificationMapper, NotificationEntity> implements NotificationService {
    @Resource
    NotificationMapper notificationMapper;

    @Override
    public List<NotificationDTO> GetMyNotification(Integer userId){
        //第一个参数是接受返回参数的类，第二个参数中的类型是连接中的左表对应的DO
        List<NotificationDTO> myNotificationList = notificationMapper.selectJoinList(NotificationDTO.class, new MPJLambdaWrapper<NotificationEntity>()
                .selectAll(NotificationEntity.class)
                .leftJoin(NotReceiverEntity.class, NotReceiverEntity::getNotificationId, NotificationEntity::getNotificationId)
                .eq(NotReceiverEntity::getReceiverId, userId));
        for(NotificationDTO myNotification : myNotificationList){
            myNotification.setReceiverId(userId);
        }
        return myNotificationList;
//        List<NotificationDTO> myNotificationList = notificationMapper.selectJoinList(NotificationDTO.class, new MPJLambdaWrapper<NotificationEntity>()
//                .selectAll(NotificationEntity.class)
//                .eq(NotificationEntity::getSendUserId, userId));
//        return myNotificationList;
    }
    @Override
    public List<NotificationDTO> GetAllNotification(){
        //第一个参数是接受返回参数的类，第二个参数中的类型是连接中的左表对应的DO
        List<NotificationDTO> notificationList = notificationMapper.selectJoinList(NotificationDTO.class, new MPJLambdaWrapper<NotificationEntity>()
                .selectAll(NotificationEntity.class)
                .select(NotReceiverEntity::getReceiverId)
                .leftJoin(NotReceiverEntity.class, NotReceiverEntity::getNotificationId, NotificationEntity::getNotificationId));

        return notificationList;
//        List<NotificationDTO> myNotificationList = notificationMapper.selectJoinList(NotificationDTO.class, new MPJLambdaWrapper<NotificationEntity>()
//                .selectAll(NotificationEntity.class)
//                .eq(NotificationEntity::getSendUserId, userId));
//        return myNotificationList;
    }

}
