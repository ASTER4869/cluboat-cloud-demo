package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.BudgetsEntity;
import com.cluboat.springcloud.mapper.BudgetsMapper;
import com.cluboat.springcloud.service.BudgetsService;
import org.springframework.stereotype.Service;

@Service
public class BudgetsServiceImpl extends ServiceImpl<BudgetsMapper, BudgetsEntity> implements BudgetsService {
}
