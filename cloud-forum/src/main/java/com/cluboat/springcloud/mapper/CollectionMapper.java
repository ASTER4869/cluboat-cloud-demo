package com.cluboat.springcloud.mapper;

import com.cluboat.springcloud.entity.CollectionEntity;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CollectionMapper extends MPJBaseMapper<CollectionEntity> {
}
