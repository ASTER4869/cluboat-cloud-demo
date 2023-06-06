package com.cluboat.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cluboat.springcloud.entity.ActivityApplyEntity;
import com.cluboat.springcloud.entity.DTO.GetActivityApplyDTO;
import com.cluboat.springcloud.entity.param.ActivityApplyClubParam;

import java.util.List;


public interface ActivityApplyService extends IService<ActivityApplyEntity> {
    public String applyForActivity(ActivityApplyEntity activityApply);

    public List<ActivityApplyEntity> getActivityApplyList(ActivityApplyClubParam param);
}
