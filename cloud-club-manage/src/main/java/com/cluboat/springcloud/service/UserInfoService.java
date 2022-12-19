package com.cluboat.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cluboat.springcloud.entity.DTO.GetClubStaffDTO;
import com.cluboat.springcloud.entity.UserInfoEntity;
import com.cluboat.springcloud.entity.DTO.UserInfoDTO;

import java.util.List;

public interface UserInfoService extends IService<UserInfoEntity> {
    public List<GetClubStaffDTO> GetAllStaffByUserId(int id);
}
