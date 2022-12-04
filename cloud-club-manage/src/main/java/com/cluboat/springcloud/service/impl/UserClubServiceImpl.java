package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.UserClubEntity;
import com.cluboat.springcloud.mapper.UserClubMapper;
import com.cluboat.springcloud.service.UserClubService;
import org.springframework.stereotype.Service;

@Service
public class UserClubServiceImpl extends ServiceImpl<UserClubMapper, UserClubEntity> implements UserClubService {

}
