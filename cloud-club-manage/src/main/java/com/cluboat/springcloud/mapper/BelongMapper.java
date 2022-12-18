package com.cluboat.springcloud.mapper;

import com.cluboat.springcloud.entity.BelongEntity;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BelongMapper extends MPJBaseMapper<BelongEntity> {
}