package com.cluboat.springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cluboat.springcloud.entity.apply.AdminApplyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminApplyMapper extends BaseMapper<AdminApplyEntity> {
}
