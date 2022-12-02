package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.PubnotEntity;
import com.cluboat.springcloud.mapper.PubnotMapper;
import com.cluboat.springcloud.service.PubnotService;
import org.springframework.stereotype.Service;

@Service
public class PubnotServiceImpl extends ServiceImpl<PubnotMapper, PubnotEntity> implements PubnotService {
}
