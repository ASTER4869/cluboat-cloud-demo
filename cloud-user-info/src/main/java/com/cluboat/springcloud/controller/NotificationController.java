package com.cluboat.springcloud.controller;


import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.NotificationEntity;
import com.cluboat.springcloud.entity.dto.NotificationDTO;
import com.cluboat.springcloud.service.NotificationService;
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

    @GetMapping("/{userId}")
    public CommonResult getMyNotification(@PathVariable Integer userId) {
        List<NotificationDTO> notificationList = notificationService.GetMyNotification(userId);
        if(notificationList.isEmpty()!=true){
            return new CommonResult(200,"查询成功", notificationList);
        }
        else{
            return new CommonResult(444,"无记录");
        }
    }
}
