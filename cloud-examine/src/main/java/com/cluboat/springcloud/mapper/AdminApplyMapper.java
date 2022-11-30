package com.cluboat.springcloud.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.cluboat.springcloud.entity.apply.AdminApplyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminApplyMapper extends MPJBaseMapper<AdminApplyEntity> {
}
