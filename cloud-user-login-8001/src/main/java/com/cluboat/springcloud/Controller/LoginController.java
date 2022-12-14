package com.cluboat.springcloud.Controller;

import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.SysAdminEntity;
import com.cluboat.springcloud.entity.UserEntity;
import com.cluboat.springcloud.entity.param.LoginParam;
import com.cluboat.springcloud.entity.param.PasswordParam;
import com.cluboat.springcloud.service.SysAdminService;
import com.cluboat.springcloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping
//@RequestMapping("/login")

public class LoginController {
    @Resource UserService userService;
    @Resource SysAdminService adminService;

    @PostMapping("/login")
    public CommonResult Login(@RequestBody LoginParam param){
        if (param.getIsAdmin()==1){
            SysAdminEntity admin = adminService.lambdaQuery().eq(SysAdminEntity::getAdminAccount, param.getLoginAccount())
                    .eq(SysAdminEntity::getAdminPassword, param.getInputPassword()).one();
            if (admin!=null){
                return new CommonResult<>(200, "登录成功", admin.getAdminId());
            }
        }
        else {
            UserEntity user = userService.lambdaQuery().eq(UserEntity::getUserPhone, param.getLoginAccount())
                    .eq(UserEntity::getUserPassword, param.getInputPassword()).one();
            if (user!=null){
                return new CommonResult<>(200, "登录成功", user.getUserId());
            }
        }

        return new CommonResult<>(400, "登录失败，账号或密码错误");
    }

    @PutMapping ("/password")
    public CommonResult ChangePassword(@RequestBody PasswordParam param){
        UserEntity user = userService.getById(param.getUserId());

        boolean res = (user.getUserPassword()).equals(param.getInputPassword());
        if (res==false)
            return new CommonResult<>(400, "修改失败， 密码错误");
        user.setUserPassword(param.getNewPassword());
        userService.updateById(user);
        return new CommonResult<>(200, "修改成功");
    }


}