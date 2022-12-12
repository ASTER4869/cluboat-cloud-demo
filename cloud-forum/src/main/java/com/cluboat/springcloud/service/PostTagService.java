package com.cluboat.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cluboat.springcloud.entity.dto.PostTagDTO;
import com.cluboat.springcloud.entity.PostTagEntity;

import java.util.List;

public interface PostTagService extends IService<PostTagEntity> {
    public List<PostTagDTO> GetPostTagListByPostId(Integer postId);

    public int DeleteByPostId(Integer postId);
}
