package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.BelongEntity;
import com.cluboat.springcloud.entity.DTO.GetClubStaffDTO;
import com.cluboat.springcloud.entity.UserInfoEntity;
import com.cluboat.springcloud.entity.DTO.UserInfoDTO;
import com.cluboat.springcloud.mapper.UserInfoMapper;
import com.cluboat.springcloud.service.UserInfoService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity> implements UserInfoService {
    @Resource
    UserInfoMapper userInfoMapper;

    @Override
    public  List<GetClubStaffDTO> GetAllStaffByUserId(int clubId) {
        List<GetClubStaffDTO> userInfoDTOList = userInfoMapper.selectJoinList(GetClubStaffDTO.class,
                new MPJLambdaWrapper<UserInfoEntity>()
                        .select(UserInfoEntity::getUserId,UserInfoEntity::getUserName)
                        .select(BelongEntity::getPermission, BelongEntity::getState)
                        .leftJoin(BelongEntity.class, BelongEntity::getUserId, UserInfoEntity::getUserId)
                        .eq(BelongEntity::getClubId, clubId));
        return userInfoDTOList;
    }
}
