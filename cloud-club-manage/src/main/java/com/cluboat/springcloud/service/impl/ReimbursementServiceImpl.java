package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.ReimbursementsEntity;
import com.cluboat.springcloud.mapper.ReimbursementMapper;
import com.cluboat.springcloud.service.ReimbursementService;
import org.springframework.stereotype.Service;

@Service
public class ReimbursementServiceImpl extends ServiceImpl<ReimbursementMapper, ReimbursementsEntity> implements ReimbursementService {
}
