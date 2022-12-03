package com.cluboat.springcloud.controller;


import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.ActivityEntity;
import com.cluboat.springcloud.entity.apply.ActivityApplyEntity;
import com.cluboat.springcloud.service.ActivityApplyService;
import com.cluboat.springcloud.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/activityapply")
public class ActivityApplyController {


    @Resource
    private ActivityApplyService activityApplyService;
    @Resource
    private ActivityService activityService;


    @GetMapping("/{id}")
    public CommonResult getActivityApplyById(@PathVariable("id") int id) {
        ActivityApplyEntity activityApply = activityApplyService.getById(id);
        log.info("****插入结果：{payment}");
        if (activityApply != null) {
            return new CommonResult(200, "查询成功", activityApply);
        } else {
            return new CommonResult(444, "无记录");
        }
    }

    @DeleteMapping("/{id}")
    public CommonResult removeById(@PathVariable("id") int id) {
        boolean isSuccess = activityApplyService.removeById(id);
        if (isSuccess)
            return new CommonResult(200, "删除成功");
        else
            return new CommonResult(400, "删除失败");

    }

    @PostMapping("/{id}")
    public CommonResult updateById(@PathVariable("id") int id,@RequestParam byte state) {
        ActivityApplyEntity activityApply = activityApplyService.getById(id);
        activityApply.setActivityApplyIsPass(state);
        ActivityEntity activity = new ActivityEntity();
        boolean isSuccess = activityApplyService.updateById(activityApply);
        log.info("****插入结果：{state}");
<<<<<<< Updated upstream
        if(state==1) {
            activity.setActivityIsPass(state);
            activity.setClubId(activityApply.getClubId());
            isSuccess = activityService.save(activity);
        }
=======
        if(state==1)
            activity.setActivityIsPass(state);
            activity.setClubId(activityApply.getClubId());
            isSuccess=activityService.save(activity);
>>>>>>> Stashed changes




        if (isSuccess)
            return new CommonResult(200, "修改成功");
        else
            return new CommonResult(400, "修改失败");

    }
}
