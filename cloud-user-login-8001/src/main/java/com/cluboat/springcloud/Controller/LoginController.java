package com.cluboat.springcloud.Controller;

import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.UserEntity;
import com.cluboat.springcloud.entity.param.LoginParam;
import com.cluboat.springcloud.entity.param.PasswordParam;
import com.cluboat.springcloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
//@RequestMapping("/login")

public class LoginController {
    @Resource UserService userService;

    @PostMapping("/login")
    public CommonResult Login(@PathVariable LoginParam param){
        UserEntity user = userService.lambdaQuery().eq(UserEntity::getUserPhone, param.getUserPhone())
                .eq(UserEntity::getUserPassword, param.getInputPassword()).getEntity();
        if (user!=null){
            return new CommonResult<>(200, "登录成功", user.getUserId());
        }
        else {
            return new CommonResult<>(400, "登录失败，账号或密码错误");
        }
    }

    @GetMapping("/password")
    public CommonResult ChangePassword(@PathVariable PasswordParam param){
        UserEntity user = userService.getById(param.getUserId());
        boolean res = (user.getUserPassword()==param.getInputPassword());
        if (res==false)
            return new CommonResult<>(400, "修改失败， 密码错误");
        user.setUserPassword(param.getNewPassword());
        userService.updateById(user);
        return new CommonResult<>(200, "修改成功");
    }


}