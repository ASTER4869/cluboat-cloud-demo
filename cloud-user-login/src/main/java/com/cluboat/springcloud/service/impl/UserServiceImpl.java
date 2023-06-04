package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.UserEntity;
import com.cluboat.springcloud.entity.param.UserParam;
import com.cluboat.springcloud.mapper.UserMapper;
import com.cluboat.springcloud.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Override
    public String loginSuccess(UserEntity userEntity){
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper
                .eq("user_phone", userEntity.getUserPhone())
                .eq("user_password", userEntity.getUserPassword());
        if(getOne(wrapper) == null){
            return "登录失败";
        }
        return "登录成功";
    }
}
