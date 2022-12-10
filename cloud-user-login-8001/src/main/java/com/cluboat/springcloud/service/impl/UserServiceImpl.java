package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.UserEntity;
import com.cluboat.springcloud.entity.param.UserParam;
import com.cluboat.springcloud.mapper.UserMapper;
import com.cluboat.springcloud.service.UserService;
import com.cluboat.springcloud.entities.User;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
}
