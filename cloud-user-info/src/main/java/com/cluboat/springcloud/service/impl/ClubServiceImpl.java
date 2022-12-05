package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.ActivityEntity;
import com.cluboat.springcloud.entity.ClubEntity;
import com.cluboat.springcloud.mapper.ActivityMapper;
import com.cluboat.springcloud.mapper.ClubMapper;
import com.cluboat.springcloud.service.ActivityService;
import com.cluboat.springcloud.service.ClubService;

import javax.annotation.Resource;

@Resource
public class ClubServiceImpl extends ServiceImpl<ClubMapper, ClubEntity> implements ClubService {
}
