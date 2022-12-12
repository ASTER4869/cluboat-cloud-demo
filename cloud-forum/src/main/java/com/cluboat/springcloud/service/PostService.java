package com.cluboat.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cluboat.springcloud.entity.PostEntity;
import com.cluboat.springcloud.entity.dto.PostListDTO;

import java.util.List;

public interface PostService extends IService<PostEntity> {
    public List<PostListDTO> GetPostListByUserId(Integer userId);
}
