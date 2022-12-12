package com.cluboat.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cluboat.springcloud.entity.TagEntity;
import com.cluboat.springcloud.entity.dto.PostTagDTO;

import java.util.List;

public interface TagService extends IService<TagEntity> {
    public List<PostTagDTO> GetTagListByClubId(Integer clubId);
}
