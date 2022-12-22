package com.cluboat.springcloud.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.ActivityEntity;
import com.cluboat.springcloud.entity.NewsEntity;
import com.cluboat.springcloud.entity.NotificationEntity;
import com.cluboat.springcloud.entity.param.ActivityChangeParam;
import com.cluboat.springcloud.entity.param.ActivityParam;
import com.cluboat.springcloud.entity.param.NewsParam;
import com.cluboat.springcloud.entity.param.NewsUpdateParam;
import com.cluboat.springcloud.service.ClubActivityService;
import com.cluboat.springcloud.service.ClubNewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/activity")

public class ClubActivityController {
    @Resource
    private ClubActivityService clubActivityService;

    /* 获取本社团所有活动 */
    @GetMapping("/{clubId}")
    public CommonResult getAllClubActivityById(@PathVariable("clubId") int id) {
        List<ActivityEntity> activityEntityList = clubActivityService.lambdaQuery()
                .eq(ActivityEntity::getClubId, id).list();

        if (activityEntityList.size() > 0) {
            return new CommonResult(200, "查询成功", activityEntityList);
        } else {
            return new CommonResult(400, "无记录");
        }
    }

    /* 删除活动 */
    @DeleteMapping("/{activityId}")
    public CommonResult removeActivityById(@PathVariable("activityId") int activityId) {
        boolean isSuccess = clubActivityService.removeById(activityId);
        if (isSuccess) {
            return new CommonResult(200, "删除成功");
        } else {
            return new CommonResult(400, "删除失败");
        }
    }


    //* 发布某一活动 */
    @PostMapping
    public CommonResult PostActivity(@RequestBody ActivityParam activityParam) {
        ActivityEntity activity = new ActivityEntity();
        activity.setActivity(activityParam);
        activity.setActivityIsPass((byte) 1);

        if (clubActivityService.save(activity)) {
            return new CommonResult(200, "更新成功");
        } else {
            return new CommonResult(400, "更新失败");
        }
    }

    //* 改某一活动 */
    @PutMapping
    public CommonResult updateActivity(@RequestBody ActivityChangeParam activityChangeParam) {
        ActivityEntity activity = clubActivityService.getById(activityChangeParam.activityId);
        ActivityParam activityParam = new ActivityParam();
        activityParam.clubId = activityChangeParam.clubId;
        activityParam.activityArea = activityChangeParam.activityArea;
        activityParam.activityContent = activityChangeParam.activityContent;
        activityParam.activityName = activityChangeParam.activityName;
        activityParam.activityStartTime = activityChangeParam.activityStartTime;
        activityParam.activityEndTime = activityChangeParam.activityEndTime;
        activity.setActivity(activityParam);
        //activity.setActivityIsPass((byte) 1);  //重新送审

        if (clubActivityService.updateById(activity)) {
            return new CommonResult(200, "更新成功");
        } else {
            return new CommonResult(400, "更新失败");
        }
    }
}


