package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.ReimbursementsEntity;
import com.cluboat.springcloud.mapper.ReimApplyMapper;
import com.cluboat.springcloud.service.ReimApplyService;
import org.springframework.stereotype.Service;

@Service
public class ReimApplyServiceImpl extends ServiceImpl<ReimApplyMapper, ReimbursementsEntity> implements ReimApplyService {
}
