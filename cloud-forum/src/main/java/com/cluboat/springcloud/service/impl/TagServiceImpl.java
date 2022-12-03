package com.cluboat.springcloud.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.TagEntity;
import com.cluboat.springcloud.entity.dto.PostTagDTO;
import com.cluboat.springcloud.mapper.TagMapper;
import com.cluboat.springcloud.service.TagService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, TagEntity> implements TagService {
    @Resource
    TagMapper tagMapper;

    @Override
    public List<PostTagDTO> GetTagListByClubId(Integer clubId){
        List<PostTagDTO> tagList = tagMapper.selectJoinList(PostTagDTO.class,
                new MPJLambdaWrapper<TagEntity>()
                        .select(TagEntity::getTagName)
                        .eq(TagEntity::getClubId,clubId)
        );
        return tagList;
    }
}
