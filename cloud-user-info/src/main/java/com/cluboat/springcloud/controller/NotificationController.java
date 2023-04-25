package com.cluboat.springcloud.controller;


import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.UserInfoEntity;
import com.cluboat.springcloud.entity.dto.NotificationDTO;
import com.cluboat.springcloud.service.NotificationService;
import com.cluboat.springcloud.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/notification")
public class NotificationController {
    @Resource
    NotificationService notificationService;

    @Resource
    UserInfoService userInfoService;


    @GetMapping("/{userId}")
    public CommonResult getMyNotification(@PathVariable Integer userId) {
        List<NotificationDTO> notificationList = notificationService.GetMyNotification(userId);
        if(notificationList.isEmpty()!=true){
            for (NotificationDTO notification : notificationList){
                //如果是用户发的
                if(notification.getSenderType() == 0){
                    UserInfoEntity userInfoEntity = userInfoService.getById(notification.getSendUserId());
                    String senderName = userInfoEntity.getUserName();
                    notification.setSenderName(senderName);
                }
                //如果是社联发的
                else if (notification.getSenderType() == 1){
                    notification.setSenderName("社联管理员" + notification.getSendAdminId());
                }
                //如果是系统发的
                else {
                    notification.setSenderName("系统");
                }
            }
            return new CommonResult(200,"查询成功", notificationList);
        }
        else{
            return new CommonResult(444,"无记录");
        }
    }

    @GetMapping()
    public CommonResult getAllNotification() {
        List<NotificationDTO> notificationList = notificationService.GetAllNotification();
        if(notificationList.isEmpty()!=true){
            for (NotificationDTO notification : notificationList){
                //如果是用户发的
                if(notification.getSenderType() == 0){
                    UserInfoEntity userInfoEntity = userInfoService.getById(notification.getSendUserId());
                    String senderName = userInfoEntity.getUserName();
                    notification.setSenderName(senderName);
                }
                //如果是社联发的
                else if (notification.getSenderType() == 1){
                    notification.setSenderName("社联管理员" + notification.getSendAdminId());
                }
                //如果是系统发的
                else {
                    notification.setSenderName("系统");
                }
            }
            return new CommonResult(200,"查询成功", notificationList);
        }
        else{
            return new CommonResult(444,"无记录");
        }
    }

}
