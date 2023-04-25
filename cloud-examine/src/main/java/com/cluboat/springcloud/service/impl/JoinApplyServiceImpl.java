package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.JoinApplyEntity;
import com.cluboat.springcloud.mapper.JoinApplyMapper;
import com.cluboat.springcloud.service.JoinApplyService;
import org.springframework.stereotype.Service;

@Service
public class JoinApplyServiceImpl extends ServiceImpl<JoinApplyMapper, JoinApplyEntity> implements JoinApplyService {
}
