package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.LikesEntity;
import com.cluboat.springcloud.mapper.LikesMapper;
import com.cluboat.springcloud.service.LikesService;
import org.springframework.stereotype.Service;

@Service
public class LikesServiceImpl  extends ServiceImpl<LikesMapper, LikesEntity> implements LikesService {
}
