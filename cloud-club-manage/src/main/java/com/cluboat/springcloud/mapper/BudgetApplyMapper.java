package com.cluboat.springcloud.mapper;


import com.cluboat.springcloud.entity.apply.BudgetApplyEntity;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BudgetApplyMapper  extends MPJBaseMapper<BudgetApplyEntity> {
}
