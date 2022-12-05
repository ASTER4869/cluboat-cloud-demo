package com.cluboat.springcloud.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.ClubEntity;
import com.cluboat.springcloud.entity.UserClubEntity;
import com.cluboat.springcloud.entity.dto.MyClubDTO;
import com.cluboat.springcloud.mapper.UserClubMapper;
import com.cluboat.springcloud.service.UserClubService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserClubServiceImpl extends ServiceImpl<UserClubMapper, UserClubEntity> implements UserClubService {
    @Resource
    UserClubMapper userClubMapper;

    @Override
    public List<MyClubDTO> GetMyClub(Integer userId){
        List<MyClubDTO> myClub = userClubMapper.selectJoinList(MyClubDTO.class, new MPJLambdaWrapper<UserClubEntity>()
            .selectAll(ClubEntity.class)
            .leftJoin(ClubEntity.class, ClubEntity::getClubId, UserClubEntity::getClubId)
            .eq(UserClubEntity::getUserId, userId));
        return myClub;
    }
}
