package com.cluboat.springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cluboat.springcloud.entities.user;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<user> {

}
