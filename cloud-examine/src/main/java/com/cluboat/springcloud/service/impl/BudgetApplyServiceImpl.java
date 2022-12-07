package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.apply.BudgetApplyEntity;
import com.cluboat.springcloud.mapper.BudgetApplyMapper;
import com.cluboat.springcloud.service.BudgetApplyService;
import org.springframework.stereotype.Service;

@Service
public class BudgetApplyServiceImpl extends ServiceImpl<BudgetApplyMapper, BudgetApplyEntity> implements BudgetApplyService {
}
