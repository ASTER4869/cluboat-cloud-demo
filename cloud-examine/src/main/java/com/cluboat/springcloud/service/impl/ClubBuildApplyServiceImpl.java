package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.mapper.ClubBuildApplyMapper;
import com.cluboat.springcloud.entity.apply.ClubBuildApplyEntity;
import com.cluboat.springcloud.service.ClubBuildApplyService;
import org.springframework.stereotype.Service;

@Service
public class ClubBuildApplyServiceImpl extends ServiceImpl<ClubBuildApplyMapper, ClubBuildApplyEntity> implements ClubBuildApplyService {
}
