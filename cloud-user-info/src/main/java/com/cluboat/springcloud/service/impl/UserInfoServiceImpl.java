package com.cluboat.springcloud.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.UserEntity;
import com.cluboat.springcloud.entity.UserInfoEntity;
import com.cluboat.springcloud.entity.dto.PersonInfoDTO;
import com.cluboat.springcloud.entity.param.PutPersonInfoParam;
import com.cluboat.springcloud.mapper.UserInfoMapper;
import com.cluboat.springcloud.service.UserInfoService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.experimental.var;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity> implements UserInfoService {

    private String blacklist = "~!@#$%^&*()_+|`-=\\{}[]:\";'<>/";

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

    @Override
    public List<PersonInfoDTO> GetAllPersonInfo(){
        List<PersonInfoDTO> personInfoList = userInfoMapper.selectJoinList(PersonInfoDTO.class, new MPJLambdaWrapper<UserInfoEntity>()
                .selectAll(UserInfoEntity.class)
                .select(UserEntity::getUserPhone)
                .leftJoin(UserEntity.class, UserEntity::getUserId, UserInfoEntity::getUserId));
        return personInfoList;
    }
    @Override
    public String changeUserInfo(UserInfoEntity userInfoEntity){
        if(userInfoEntity.getUserSign().toString().length() >= 200){
            return "个人签名超过字数";
        }
        for(char c : blacklist.toCharArray()){
            if(userInfoEntity.getUserName().contains(String.valueOf(c))){
                return "用户昵称违法";
            }
        }

        QueryWrapper<UserInfoEntity> wrapper = new QueryWrapper<UserInfoEntity>();
        wrapper.eq("user_name", userInfoEntity.getUserName());
        if(list(wrapper).isEmpty()){
            return "用户昵称已存在";
        }

        return "更改成功";

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
