package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.ReimApplyEntity;
import com.cluboat.springcloud.mapper.ReimApplyMapper;
import com.cluboat.springcloud.service.ReimApplyService;
import org.springframework.stereotype.Service;

@Service
public class ReimApplyServiceImpl extends ServiceImpl<ReimApplyMapper, ReimApplyEntity> implements ReimApplyService {
}
