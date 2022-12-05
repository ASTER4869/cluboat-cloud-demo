package com.cluboat.springcloud.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cluboat.springcloud.entity.AdminApplyEntity;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminApplyMapper extends MPJBaseMapper<AdminApplyEntity> {

}
