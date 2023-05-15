package com.cluboat.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cluboat.springcloud.entity.PostEntity;
import com.cluboat.springcloud.entity.dto.PostListDTO;
import com.cluboat.springcloud.entity.param.PostAddParam;
import com.cluboat.springcloud.entity.param.PostGetByClubIdParam;
import com.cluboat.springcloud.entity.param.PostGetParam;

import java.util.List;

public interface PostService extends IService<PostEntity> {
    public List<PostListDTO> GetPostListByParam(PostGetParam param);

    // 根据clubId和status获取帖子列表
    public List<PostListDTO> GetPostListByClubId(PostGetByClubIdParam param);

    public List<PostListDTO> GetAllPostList();

//    public List<Integer> GetPostIdByBasicInfo(PostAddParam postAddParam);
}
