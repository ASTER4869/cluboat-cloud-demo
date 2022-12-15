package com.cluboat.springcloud.Controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cluboat.springcloud.entity.UserEntity;
import com.cluboat.springcloud.entity.UserInfoEntity;
import com.cluboat.springcloud.service.UserInfoService;
import com.cluboat.springcloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.param.UserParam;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

//@RequestMapping(value = "/employees/{id}",method = RequestMethod.GET)

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private UserInfoService userInfoService;

    @PostMapping  // sign up and create user
    public CommonResult AddUser(@RequestBody UserParam param){
        UserEntity user_check = userService.lambdaQuery().eq(UserEntity::getUserPhone, param.getUserPhone()).one();
        if (user_check!=null)
            return new CommonResult(400, "该手机已注册，请登录账号");
        UserEntity user = new UserEntity();
        UserInfoEntity info = new UserInfoEntity();
        user.setUserPhone(param.getUserPhone());
        user.setUserPassword(param.getUserPassword());
        boolean res1 = userService.save(user);
        user = userService.lambdaQuery().eq(UserEntity::getUserPhone, param.getUserPhone()).one();

        // set original user info
        info.setUserId(user.getUserId());
        info.setUserName("user"+param.getUserPhone());
        info.setUserSexual("保密");
        info.setUserCreateTime(new Timestamp(System.currentTimeMillis()));
        info.setUserPhotoUrl("https://s2.loli.net/2022/11/20/sK9DCVWY7qLyIhw.jpg");
        info.setUserSign("该用户什么都没写");
        boolean res2 = userInfoService.save(info);
        boolean res = res1&&res2;
        if(res)
            return new CommonResult(200, "添加成功");
        else
            return  new CommonResult(400, "操作失败");
    }

}
