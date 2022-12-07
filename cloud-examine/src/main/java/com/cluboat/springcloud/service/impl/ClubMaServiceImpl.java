package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.UserInfoEntity;
import com.cluboat.springcloud.mapper.ClubMaMapper;
import com.cluboat.springcloud.service.ClubMaService;
import org.springframework.stereotype.Service;

@Service
public class ClubMaServiceImpl extends ServiceImpl<ClubMaMapper, UserInfoEntity> implements ClubMaService {
}
