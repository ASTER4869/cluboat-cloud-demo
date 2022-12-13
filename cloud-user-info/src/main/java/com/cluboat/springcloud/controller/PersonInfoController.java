package com.cluboat.springcloud.controller;


import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.UserInfoEntity;
import com.cluboat.springcloud.entity.UserEntity;
import com.cluboat.springcloud.entity.dto.PersonInfoDTO;
import com.cluboat.springcloud.entity.param.PutPersonInfoParam;
import com.cluboat.springcloud.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/person-info")
public class PersonInfoController {
    @Resource
    private UserInfoService userInfoService;


    @GetMapping("/{userId}")
    public CommonResult getPersonInfo(@PathVariable Integer userId) {
        PersonInfoDTO personInfoDTO = userInfoService.GetPersonInfo(userId);
        if(personInfoDTO!=null){
            return new CommonResult(200,"查询成功", personInfoDTO);
        }
        else {
            return new CommonResult(444,"无记录");
        }
//        由于返回的参数需要自定义，不是按userInfo表来返回的，所以不能直接getById
//        return new CommonResult(200,"查询成功", userInfoService.getById(userId));
    }

    @PutMapping
    public CommonResult updatePersonInfo(@RequestBody PutPersonInfoParam param) {
        UserInfoEntity userInfoEntity = userInfoService.getById(param.getUserId());
//        //如果没有该user的记录
//        if(userInfoEntity==null){
//            return new CommonResult(444,"无记录");
//        }
//        userInfoEntity.setUserName(param.getUserName());
//        userEntity.setUserPhone(param.getUserPhone());
//        userInfoEntity.setUserSexual(param.getUserSexual());
//        userInfoEntity.setUserPhotoUrl(param.getUserPhotoUrl());
//        userInfoEntity.setUserSign(param.getUserSign());
//        boolean isSuccessful = userInfoService.updateById(userInfoEntity);
//
//        if(isSuccessful == false){
//            return new CommonResult(400,"修改失败");
//        }
        return new CommonResult(200,"修改成功");
    }
}
