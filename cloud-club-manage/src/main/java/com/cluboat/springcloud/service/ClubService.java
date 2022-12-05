package com.cluboat.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cluboat.springcloud.entity.ClubEntity;
import com.cluboat.springcloud.entity.dto.ClubDTO;

public interface ClubService extends IService<ClubEntity> {
    public ClubDTO GetClub(Integer clubId);
}
