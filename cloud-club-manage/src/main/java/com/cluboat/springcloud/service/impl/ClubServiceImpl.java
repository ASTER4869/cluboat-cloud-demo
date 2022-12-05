package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.ActivityEntity;
import com.cluboat.springcloud.entity.AdminApplyEntity;
import com.cluboat.springcloud.entity.ClubEntity;
import com.cluboat.springcloud.entity.dto.ClubDTO;
import com.cluboat.springcloud.mapper.ClubMapper;
import com.cluboat.springcloud.service.ClubService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ClubServiceImpl extends ServiceImpl<ClubMapper, ClubEntity> implements ClubService {
    @Resource
    ClubMapper clubMapper;

    @Override
    public ClubDTO GetClub(Integer clubId){
        ClubDTO club = clubMapper.selectJoinOne(ClubDTO.class, new MPJLambdaWrapper<ClubEntity>()
                .selectAll(ClubEntity.class)
                .eq(ClubEntity::getClubId, clubId));
        return club;
    }
}
