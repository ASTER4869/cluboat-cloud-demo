package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.NewsEntity;
import com.cluboat.springcloud.mapper.ClubNewsMapper;
import com.cluboat.springcloud.service.ClubNewsService;
import org.springframework.stereotype.Service;

@Service
public class ClubNewsServiceImpl extends ServiceImpl<ClubNewsMapper, NewsEntity> implements ClubNewsService {
}