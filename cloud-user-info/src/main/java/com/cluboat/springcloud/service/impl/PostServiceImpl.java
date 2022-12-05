package com.cluboat.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.NotReceiverEntity;
import com.cluboat.springcloud.entity.PostEntity;
import com.cluboat.springcloud.mapper.NotReceiverMapper;
import com.cluboat.springcloud.mapper.PostMapper;
import com.cluboat.springcloud.service.NotReceiverService;
import com.cluboat.springcloud.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl  extends ServiceImpl<PostMapper, PostEntity> implements PostService {


}
