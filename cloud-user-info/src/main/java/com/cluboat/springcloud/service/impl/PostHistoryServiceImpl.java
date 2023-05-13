package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.PostHistoryEntity;
import com.cluboat.springcloud.mapper.PostHistoryMapper;
import com.cluboat.springcloud.service.PostHistoryService;
import org.springframework.stereotype.Service;

@Service
public class PostHistoryServiceImpl  extends ServiceImpl<PostHistoryMapper, PostHistoryEntity> implements PostHistoryService {
}
