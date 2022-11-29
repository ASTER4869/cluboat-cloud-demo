package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.mapper.ActivityApplyMapper;
import com.cluboat.springcloud.service.ActivityApplyService;
import org.springframework.stereotype.Service;
import com.cluboat.springcloud.entity.apply.ActivityApplyEntity;




@Service
public class ActivityApplyServiceImpl extends ServiceImpl<ActivityApplyMapper, ActivityApplyEntity> implements ActivityApplyService {
}
