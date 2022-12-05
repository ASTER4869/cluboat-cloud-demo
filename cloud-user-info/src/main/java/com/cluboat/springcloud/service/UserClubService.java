package com.cluboat.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cluboat.springcloud.entity.UserClubEntity;
import com.cluboat.springcloud.entity.dto.MyClubDTO;

import java.util.List;

public interface UserClubService extends IService<UserClubEntity> {
    public List<MyClubDTO> GetMyClub(Integer userId);
}
