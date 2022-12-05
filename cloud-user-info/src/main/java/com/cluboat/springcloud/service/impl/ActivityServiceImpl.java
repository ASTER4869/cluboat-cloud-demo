package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.ActivityEntity;
import com.cluboat.springcloud.entity.FollowEntity;
import com.cluboat.springcloud.entity.dto.MyActivityDTO;
import com.cluboat.springcloud.mapper.ActivityMapper;
import com.cluboat.springcloud.mapper.FollowMapper;
import com.cluboat.springcloud.service.ActivityService;
import com.cluboat.springcloud.service.FollowService;
import com.github.yulichang.query.MPJQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActivityServiceImpl  extends ServiceImpl<ActivityMapper, ActivityEntity> implements ActivityService {
    @Resource
    ActivityMapper activityMapper;

    @Override
    public List<MyActivityDTO> GetMyActivity(Integer userId){
        //第一个参数是接受返回参数的类，第二个参数中的类型是连接中的左表对应的DO
        List<MyActivityDTO> myActivityList = activityMapper.selectJoinList(MyActivityDTO.class, new MPJLambdaWrapper<ActivityEntity>()
            .selectAll(ActivityEntity.class)
            .leftJoin(FollowEntity.class, FollowEntity::getActivityId, ActivityEntity::getActivityId)
            .eq(FollowEntity::getUserId, userId));
        return myActivityList;
    }
}
