package com.cluboat.springcloud.Controller;

import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.ActivityEntity;
import com.cluboat.springcloud.entity.NewsEntity;
import com.cluboat.springcloud.entity.NotificationEntity;
import com.cluboat.springcloud.entity.param.NotificationParam;
import com.cluboat.springcloud.service.ClubNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/notification")
public class ClubNotificationController {
    @Resource
    private ClubNotificationService notificationService;

    /* 根据社团id返回某社团所有新闻列表 */
    @GetMapping("/{sendUserId}")
    public CommonResult getAllClubNotificationById(@PathVariable("sendUserId") int id) {
        List<NotificationEntity> notificationEntityList = notificationService.lambdaQuery()
                .eq(NotificationEntity::getSendUserId, id).list();
        if (notificationEntityList.size() > 0) {
            return new CommonResult(200, "查询成功",notificationEntityList);
        } else {
            return new CommonResult(400, "无记录");
        }
    }

    /* 发布通知 */
    @PostMapping
    public CommonResult createNotification(@RequestBody NotificationParam notificationParam) {
        NotificationEntity notification = new  NotificationEntity();
        notificationParam.notificationTime = new Timestamp(System.currentTimeMillis());
        notification.setNotification(notificationParam);
        try {
            notificationService.save(notification);
            return new CommonResult(200, "修改成功");
        } catch (Exception e) {
            return new CommonResult(400, "修改失败", e);
        }

    }

}
