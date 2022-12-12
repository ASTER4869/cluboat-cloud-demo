package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.ActivityEntity;
import com.cluboat.springcloud.mapper.ClubActivityMapper;

import com.cluboat.springcloud.service.ClubActivityService;
import com.cluboat.springcloud.service.ClubService;
import org.springframework.stereotype.Service;

@Service
public class ClubActivityServiceImpl extends ServiceImpl<ClubActivityMapper, ActivityEntity> implements ClubActivityService {
}
