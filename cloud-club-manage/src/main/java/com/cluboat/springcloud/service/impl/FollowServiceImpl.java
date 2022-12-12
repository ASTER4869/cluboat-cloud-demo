package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.FollowEntity;
import com.cluboat.springcloud.mapper.FollowMapper;
import com.cluboat.springcloud.service.FollowService;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, FollowEntity> implements FollowService {
}
