package com.cluboat.springcloud.Controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cluboat.springcloud.entity.UserEntity;
import com.cluboat.springcloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.param.UserParam;

import javax.annotation.Resource;
import java.util.List;

//@RequestMapping(value = "/employees/{id}",method = RequestMethod.GET)

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping  // sign up and create user
    public CommonResult AddUser(@RequestBody UserParam param){
        UserEntity user = new UserEntity();
        user.setUserPhone(param.getUserPhone());
        user.setUserPassword(param.getUserPassword());
        boolean res = userService.save(user);
        if(res)
            return new CommonResult(200, "添加成功");
        else
            return  new CommonResult(400, "操作失败");
    }

}
