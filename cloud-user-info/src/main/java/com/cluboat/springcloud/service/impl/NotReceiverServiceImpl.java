package com.cluboat.springcloud.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.NotReceiverEntity;
import com.cluboat.springcloud.mapper.NotReceiverMapper;
import com.cluboat.springcloud.service.NotReceiverService;
import org.springframework.stereotype.Service;

@Service
public class NotReceiverServiceImpl extends ServiceImpl<NotReceiverMapper, NotReceiverEntity> implements NotReceiverService {
}
