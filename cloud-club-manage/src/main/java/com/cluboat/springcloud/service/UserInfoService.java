package com.cluboat.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cluboat.springcloud.entity.ClubEntity;
import com.cluboat.springcloud.entity.UserInfoEntity;
import com.cluboat.springcloud.entity.param.UserInfoDTO;

import java.util.List;

public interface UserInfoService extends IService<UserInfoEntity> {
    public List<UserInfoDTO> GetAllStaffByUserId(int id);
}
