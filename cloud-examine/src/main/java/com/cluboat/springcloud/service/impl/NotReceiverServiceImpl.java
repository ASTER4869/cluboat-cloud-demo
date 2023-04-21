package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.NotReceiverEntity;
import com.cluboat.springcloud.entity.NotificationEntity;
import com.cluboat.springcloud.entity.dto.NotificationDTO;
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
    public List<NotificationDTO> GetSendNotification(GetNotificationParam notificationParam){

        List<NotificationDTO> sendNotificationList;
//        //第一个参数是接受返回参数的类，第二个参数中的类型是连接中的左表对应的DO
//        List<NotificationDTO> sendNotificationList = notificationMapper.selectJoinList(NotificationDTO.class, new MPJLambdaWrapper<NotificationEntity>()
//                .selectAll(NotificationEntity.class)
////                .leftJoin(NotReceiverEntity.class, NotReceiverEntity::getNotificationId, NotificationEntity::getNotificationId)
////                .eq(NotificationEntity::getSendAdminId, notificationParam.id));
//        );


        if(notificationParam.type == 0){
            //第一个参数是接受返回参数的类，第二个参数中的类型是连接中的左表对应的DO
            sendNotificationList = notReceiverMapper.selectJoinList(NotificationDTO.class, new MPJLambdaWrapper<NotReceiverEntity>()
                    .selectAll(NotificationEntity.class)
                    .leftJoin(NotReceiverEntity.class, NotReceiverEntity::getNotificationId, NotificationEntity::getNotificationId)
                    .eq(NotificationEntity::getSendAdminId, notificationParam.id));
        }
        else {
            //第一个参数是接受返回参数的类，第二个参数中的类型是连接中的左表对应的DO
            sendNotificationList = notReceiverMapper.selectJoinList(NotificationDTO.class, new MPJLambdaWrapper<NotReceiverEntity>()
                    .selectAll(NotificationEntity.class)
                    .leftJoin(NotReceiverEntity.class, NotReceiverEntity::getNotificationId, NotificationEntity::getNotificationId)
                    .eq(NotificationEntity::getSendUserId, notificationParam.id));
        }
        return sendNotificationList;
//        List<NotificationDTO> myNotificationList = notificationMapper.selectJoinList(NotificationDTO.class, new MPJLambdaWrapper<NotificationEntity>()
//                .selectAll(NotificationEntity.class)
//                .eq(NotificationEntity::getSendUserId, userId));
//        return myNotificationList;
    }
}
