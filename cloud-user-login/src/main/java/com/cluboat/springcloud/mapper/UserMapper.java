package com.cluboat.springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.cluboat.springcloud.entity.UserEntity;
import com.github.yulichang.base.MPJBaseMapper;


@Repository
@Mapper
public interface UserMapper extends MPJBaseMapper<UserEntity>{

}
