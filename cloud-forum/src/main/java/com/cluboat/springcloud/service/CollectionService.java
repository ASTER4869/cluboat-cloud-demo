package com.cluboat.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cluboat.springcloud.entity.CollectionEntity;
import com.cluboat.springcloud.entity.param.CollectionDeleteParam;

public interface CollectionService extends IService<CollectionEntity> {
    public int DeleteCollection(CollectionDeleteParam param);
}
