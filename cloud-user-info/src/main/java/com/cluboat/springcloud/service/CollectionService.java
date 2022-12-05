package com.cluboat.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cluboat.springcloud.entity.CollectionEntity;
import com.cluboat.springcloud.entity.dto.CollectionDTO;

import java.util.List;

public interface CollectionService extends IService<CollectionEntity> {
    public List<CollectionDTO> GetCollection(Integer userId);
}
