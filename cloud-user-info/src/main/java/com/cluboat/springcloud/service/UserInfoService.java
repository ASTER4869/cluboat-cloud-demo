package com.cluboat.springcloud.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cluboat.springcloud.entity.UserInfoEntity;
import com.cluboat.springcloud.entity.dto.PersonInfoDTO;
import com.cluboat.springcloud.entity.param.PutPersonInfoParam;

public interface UserInfoService extends IService<UserInfoEntity>{
    public PersonInfoDTO GetPersonInfo(Integer userId);

    //修改用户个人信息，返回修改的个数
//    public Integer PutPersonInfo(PutPersonInfoParam putInfo);

}
