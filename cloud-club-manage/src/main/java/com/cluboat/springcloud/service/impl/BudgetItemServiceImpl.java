package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.BudgetItemEntity;
import com.cluboat.springcloud.mapper.BudgetItemMapper;
import com.cluboat.springcloud.service.BudgetItemService;
import org.springframework.stereotype.Service;

@Service
public class BudgetItemServiceImpl extends ServiceImpl<BudgetItemMapper, BudgetItemEntity> implements BudgetItemService {
}
