package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.UserEntity;
import com.cluboat.springcloud.mapper.UserMapper;
import com.cluboat.springcloud.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Override
    public String loginJudge(UserEntity userEntity){
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_phone", userEntity.getUserPhone());
        if(userEntity.getUserPhone().isEmpty() || wrapper == null){
            return "账号不存在";
        }
        wrapper.eq("user_password", userEntity.getUserPassword());
        if(userEntity.getUserPassword().isEmpty() || wrapper == null){
            return "密码不正确";
        }
        return "登录成功";
    }

    @Override
    public String registerJudge(UserEntity userEntity){
        char[] phone = userEntity.getUserPhone().toCharArray();
        for(char c : phone){
            if(c < '0' || c > '9'){
                return "手机不合法";
            }
        }

        char[] password = userEntity.getUserPassword().toCharArray();
        for(char c : password){
            if((c < '0' || c > '9')&&(c < 'a' || c > 'z')&&(c < 'A' || c > 'Z')){
                return "密码不合法";
            }
        }

        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_phone", userEntity.getUserPhone());
        if(wrapper != null){
            return "手机已注册";
        }

        return "注册成功";







    }
}
