package com.cluboat.springcloud.mapper;

import com.cluboat.springcloud.entity.NotReceiverEntity;
import com.github.yulichang.base.MPJBaseMapper;
import com.github.yulichang.interfaces.MPJBaseJoin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NotReceiverMapper extends MPJBaseMapper<NotReceiverEntity> {
}
