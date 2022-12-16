package com.cluboat.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cluboat.springcloud.entity.BelongEntity;
import com.cluboat.springcloud.entity.dto.MyClubDTO;

import java.util.List;

public interface BelongService extends IService<BelongEntity> {
    public List<MyClubDTO> GetMyClub(Integer userId);
}
