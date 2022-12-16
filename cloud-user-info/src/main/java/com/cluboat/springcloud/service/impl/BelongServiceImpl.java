package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.BelongEntity;
import com.cluboat.springcloud.entity.ClubEntity;
import com.cluboat.springcloud.entity.UserClubEntity;
import com.cluboat.springcloud.entity.dto.MyClubDTO;
import com.cluboat.springcloud.mapper.BelongMapper;
import com.cluboat.springcloud.mapper.UserClubMapper;
import com.cluboat.springcloud.service.BelongService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BelongServiceImpl extends ServiceImpl<BelongMapper, BelongEntity> implements BelongService {
    @Resource
    BelongMapper belongMapper;

    @Override
    public List<MyClubDTO> GetMyClub(Integer userId){
        List<MyClubDTO> myClub = belongMapper.selectJoinList(MyClubDTO.class, new MPJLambdaWrapper<BelongEntity>()
                .selectAll(BelongEntity.class)
                .select(ClubEntity::getClubName)
                .select(ClubEntity::getClubImageUrl)
                .leftJoin(ClubEntity.class, ClubEntity::getClubId, BelongEntity::getClubId)
                .eq(BelongEntity::getUserId, userId));
        return myClub;
    }
}
