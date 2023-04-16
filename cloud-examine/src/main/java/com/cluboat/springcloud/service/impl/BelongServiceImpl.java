package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.Belong;
import com.cluboat.springcloud.mapper.BelongMapper;
import com.cluboat.springcloud.service.BelongService;
import org.springframework.stereotype.Service;

@Service
public class BelongServiceImpl extends ServiceImpl<BelongMapper, Belong> implements BelongService {
}
