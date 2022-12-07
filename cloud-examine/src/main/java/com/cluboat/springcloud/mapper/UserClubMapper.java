package com.cluboat.springcloud.mapper;


import com.cluboat.springcloud.entity.UserClubEntity;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserClubMapper extends MppBaseMapper<UserClubEntity> {
}
