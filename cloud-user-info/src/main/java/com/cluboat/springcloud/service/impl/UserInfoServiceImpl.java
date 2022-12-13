package com.cluboat.springcloud.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.UserEntity;
import com.cluboat.springcloud.entity.UserInfoEntity;
import com.cluboat.springcloud.entity.dto.PersonInfoDTO;
import com.cluboat.springcloud.entity.param.PutPersonInfoParam;
import com.cluboat.springcloud.mapper.UserInfoMapper;
import com.cluboat.springcloud.service.UserInfoService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity> implements UserInfoService {
    @Resource
    UserInfoMapper userInfoMapper;

    @Override
    public PersonInfoDTO GetPersonInfo(Integer userId){
        PersonInfoDTO personInfo = userInfoMapper.selectJoinOne(PersonInfoDTO.class, new MPJLambdaWrapper<UserInfoEntity>()
            .selectAll(UserInfoEntity.class)
            .select(UserEntity::getUserPhone)
            .leftJoin(UserEntity.class, UserEntity::getUserId, UserInfoEntity::getUserId)
            .eq(UserInfoEntity::getUserId, userId));
        return personInfo;
    }

    //修改用户个人信息，返回修改的个数
//    @Override
//    public Integer PutPersonInfo(PutPersonInfoParam putInfo){
//        UserInfoEntity userInfo = userInfoMapper.selectJoinOne(UserInfoEntity.class, new MPJLambdaWrapper<UserInfoEntity>()
//                .selectAll(PutPersonInfoParam.class)
//                .select(UserInfoEntity::getUserCreateTime)
//                .leftJoin(PutPersonInfoParam.class, PutPersonInfoParam::getUserId, UserInfoEntity::getUserId)
//                .eq(UserInfoEntity::getUserId, putInfo.getUserId()));
//        return userInfoMapper.updateById(userInfo);
//    }

}
