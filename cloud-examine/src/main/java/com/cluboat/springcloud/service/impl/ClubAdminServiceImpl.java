package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.ClubAdminEntity;
import com.cluboat.springcloud.mapper.ClubAdminMapper;
import com.cluboat.springcloud.service.ClubAdminService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ClubAdminServiceImpl extends MppServiceImpl<ClubAdminMapper, ClubAdminEntity> implements ClubAdminService {
}
