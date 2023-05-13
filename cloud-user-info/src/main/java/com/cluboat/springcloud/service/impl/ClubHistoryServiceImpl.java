package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.ClubHistoryEntity;
import com.cluboat.springcloud.mapper.ClubHistoryMapper;
import com.cluboat.springcloud.service.ClubHistoryService;
import org.springframework.stereotype.Service;

@Service
public class ClubHistoryServiceImpl  extends ServiceImpl<ClubHistoryMapper, ClubHistoryEntity> implements ClubHistoryService {
}
