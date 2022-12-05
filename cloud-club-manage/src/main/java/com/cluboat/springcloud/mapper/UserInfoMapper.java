package com.cluboat.springcloud.mapper;

import cn.hutool.system.UserInfo;
import com.cluboat.springcloud.entity.UserClubEntity;
import com.cluboat.springcloud.entity.UserInfoEntity;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserInfoMapper extends MPJBaseMapper<UserInfoEntity> {
}