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
    public CommonResult AddUser(@PathVariable UserParam param){
        UserEntity user = new UserEntity();
        user.setUserPhone(param.getUserPhone());
        user.setUserPassword(param.getUserPassword());
        boolean res = userService.save(user);
        if(res)
            return new CommonResult(200, "添加成功");
        else
            return  new CommonResult(400, "操作失败");
    }


//    old api version (for test demo only)

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return userService.removeById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return userService.removeByIds(ids);
    }
    @GetMapping
    public List<UserEntity> findAll() {
        return userService.list();
    }

    @GetMapping("/{id}")
    public UserEntity findOne(@PathVariable Integer id) {
        return userService.getById(id);
    }
    @GetMapping("/user/{id}")
    public CommonResult getUser(@PathVariable("id") Long id){
        UserEntity user = userService.getById(id);
        log.info("****插入结果：{payment}");
        if(user!=null){
            return new CommonResult(200,"查询成功",user);
        }else {
            return new CommonResult(400,"无记录");
        }

    }

    @GetMapping("/page")
    public Page<UserEntity> findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize) {
        return userService.page(new Page<>(pageNum,pageSize));
    }




}
