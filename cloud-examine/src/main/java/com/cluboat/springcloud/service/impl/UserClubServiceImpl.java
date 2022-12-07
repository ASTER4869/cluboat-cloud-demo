package com.cluboat.springcloud.service.impl;


import com.cluboat.springcloud.entity.UserClubEntity;
import com.cluboat.springcloud.mapper.UserClubMapper;
import com.cluboat.springcloud.service.UserClubService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserClubServiceImpl extends MppServiceImpl<UserClubMapper, UserClubEntity> implements UserClubService {
}
