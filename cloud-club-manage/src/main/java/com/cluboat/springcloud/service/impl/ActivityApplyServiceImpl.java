package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.ActivityApplyEntity;
import com.cluboat.springcloud.entity.DTO.GetActivityApplyDTO;
import com.cluboat.springcloud.entity.param.ActivityApplyClubParam;
import com.cluboat.springcloud.mapper.ActivityApplyMapper;
import com.cluboat.springcloud.mapper.ClubMapper;
import com.cluboat.springcloud.service.ActivityApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class ActivityApplyServiceImpl extends ServiceImpl<ActivityApplyMapper, ActivityApplyEntity> implements ActivityApplyService {
    private String blacklist = "~!@#$%^&*()_+|`-=\\{}[]:\";'<>/";

    @Override
    public String applyForActivity(ActivityApplyEntity activityApply){
        if(activityApply.getActivityLocation().isEmpty()){
            return "活动地点为空";
        }

        if(activityApply.getActivityDate() == null || activityApply.getActivityTime().isEmpty()){
            return "活动时间不符合规范";

        }

        QueryWrapper<ClubMapper> clubMapper = new QueryWrapper<>();
        clubMapper.eq("club_id", activityApply.getClubId());
        if(clubMapper.isEmptyOfEntity()){
            return "创建社团不存在";
        }

        for(char c : blacklist.toCharArray()){
            if(activityApply.getActivityTitle().contains(String.valueOf(c))){
                return "活动标题含有非法字符";
            }
        }

        return "活动申请提交成功";
    }

    @Override
    public List<ActivityApplyEntity> getActivityApplyList(ActivityApplyClubParam param){
        List<ActivityApplyEntity> resultList = new ArrayList<>();
        ActivityApplyEntity result = new ActivityApplyEntity();
        QueryWrapper<ActivityApplyEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("club_id", param.getClubId());
        if(wrapper.isEmptyOfEntity()){
            result.setActivityTitle("查询社团不存在");
            resultList.add(result);
        }
        else if(param.getStatus().isEmpty()){
            resultList = list(wrapper);
        }
        else{
            wrapper.eq("stauts", param.getStatus());
            if(wrapper.isEmptyOfEntity()){
                result.setActivityTitle("满足查询条件的社团活动为空");
                resultList.add(result);
            }
            else{
                resultList = list(wrapper);
            }
        }

        return resultList;


    }


}
