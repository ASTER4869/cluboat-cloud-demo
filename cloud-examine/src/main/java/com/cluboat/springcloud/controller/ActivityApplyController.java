package com.cluboat.springcloud.controller;


import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.apply.ActivityApplyEntity;
import com.cluboat.springcloud.service.ActivityApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/activityapply")
public class ActivityApplyController {



    @Resource
    private ActivityApplyService activityApplyService;


    @GetMapping("{id}")
    public CommonResult getActivityApplyById(@PathVariable("id") int id){
        ActivityApplyEntity activityApply = activityApplyService.getById(id);
        log.info("****插入结果：{payment}");
        if(activityApply!=null){
            return new CommonResult(200,"查询成功",activityApply);
        }else {
            return new CommonResult(444,"无记录");
        }
    }
}
