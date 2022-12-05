package com.cluboat.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cluboat.springcloud.entity.ActivityEntity;
import com.cluboat.springcloud.entity.dto.MyActivityDTO;

import java.util.List;

public interface ActivityService extends IService<ActivityEntity> {
    public List<MyActivityDTO> GetMyActivity(Integer userId);
}
