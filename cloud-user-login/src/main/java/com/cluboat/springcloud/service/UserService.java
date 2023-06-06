package com.cluboat.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cluboat.springcloud.entity.UserEntity;


public interface UserService extends IService<UserEntity>{
    String loginJudge(UserEntity userEntity);

    String registerJudge(UserEntity userEntity);

}
