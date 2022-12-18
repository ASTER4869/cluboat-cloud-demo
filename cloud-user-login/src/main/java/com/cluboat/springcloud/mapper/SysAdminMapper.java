package com.cluboat.springcloud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.cluboat.springcloud.entity.SysAdminEntity;
import com.github.yulichang.base.MPJBaseMapper;


@Repository
@Mapper
public interface SysAdminMapper extends MPJBaseMapper<SysAdminEntity>{
}
