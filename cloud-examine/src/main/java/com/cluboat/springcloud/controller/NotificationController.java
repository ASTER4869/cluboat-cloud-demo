package com.cluboat.springcloud.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.ClubAdminEntity;
import com.cluboat.springcloud.entity.NotificationEntity;
import com.cluboat.springcloud.entity.param.NotificationParam;
import com.cluboat.springcloud.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/notification")
public class NotificationController {

    @Resource
    private NotificationService notificationService;
    @GetMapping("/{id}")
    public CommonResult getNotificationById(@PathVariable("id") int id) {

        List<NotificationEntity> list = notificationService.lambdaQuery().eq(NotificationEntity::getSendUserId, id).list();
        log.info("****插入结果：{payment}");
        if (list != null) {
            return new CommonResult(200, "查询成功", list);
        } else {
            return new CommonResult(400, "无记录");
        }
    }
    @GetMapping
    public CommonResult getAllNotification() {
        List<NotificationEntity> notificationEntityList = notificationService.list();
        log.info("****插入结果：{payment}");
        if (notificationEntityList != null) {
            return new CommonResult(200, "查询成功", notificationEntityList);
        } else {
            return new CommonResult(400, "无记录");
        }
    }


    @PostMapping
    public CommonResult createNotification(NotificationParam notificationParam) {
        NotificationEntity notification = new NotificationEntity();
        notificationParam.notificationTime=new Timestamp(System.currentTimeMillis());
        System.out.println(notificationParam);
        notification.setNotification(notificationParam);
        try {
            notificationService.save(notification);
            return new CommonResult(200, "创建成功");
        } catch (Exception e){
            return new CommonResult(400, "修改失败",e);
        }
    }


}
