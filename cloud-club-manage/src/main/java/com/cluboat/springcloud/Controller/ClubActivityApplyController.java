package com.cluboat.springcloud.Controller;


import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.ActivityApplyEntity;
import com.cluboat.springcloud.entity.param.ActivityApplyParam;
import com.cluboat.springcloud.service.ActivityApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/activity-application")
public class ClubActivityApplyController {

    @Resource
    private ActivityApplyService activityApplyService;

    /* 提交活动申请 */
    @PostMapping
    public CommonResult createActivityApply(@RequestBody ActivityApplyParam activityApplyParam) {
        ActivityApplyEntity activityApply = new ActivityApplyEntity();
        activityApply.setFeedback(null);  //提交申请没有反馈！
        activityApply.setActivityApplyIsPass((byte) 0);  //初始未通过！

        activityApplyParam.activityApplyTime = new Timestamp(System.currentTimeMillis());
        activityApply.setActivityApply(activityApplyParam);
        try {
            activityApplyService.save(activityApply);
            return new CommonResult(200, "上传申请成功");
        } catch (Exception e) {
            return new CommonResult(400, "上传申请失败", e);
        }

    }

    /* 获得我提交的活动申请 */
    @GetMapping("/{userId}")
    public CommonResult getActivityApplyById(@PathVariable("userId") int id) {
        List<ActivityApplyEntity> activityApplyList = activityApplyService.lambdaQuery()
                        .eq(ActivityApplyEntity::getUserId,id).list();
        log.info("****插入结果：{payment}");
        if (activityApplyList.size() > 0) {
            return new CommonResult(200, "查询成功", activityApplyList);
        } else {
            return new CommonResult(400, "无记录");
        }
    }


}
