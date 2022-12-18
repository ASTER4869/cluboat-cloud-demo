package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.ClubEntity;
import com.cluboat.springcloud.mapper.ClubMapper;
import com.cluboat.springcloud.service.ClubService;
import org.springframework.stereotype.Service;


@Service
public class ClubServiceImpl extends ServiceImpl<ClubMapper, ClubEntity> implements ClubService {
//    @Resource
//    ClubMapper clubMapper;

//    @Override
//    public  List<ClubEntity> GetAllClubByUserId(Integer id) {
//        List<ClubEntity> clubEntityList = clubMapper.selectJoinList(ClubEntity.class,
//                new MPJLambdaWrapper<ClubEntity>()
//                        .selectAll(ClubEntity.class)
//                        .leftJoin(UserClubEntity.class, UserClubEntity::getClubId, ClubEntity::getClubId)
//                        .eq(UserClubEntity::getUserId,id));
//        return clubEntityList;
//    }
}
