package com.cluboat.springcloud.Controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cluboat.springcloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.cluboat.springcloud.entities.User;
import com.cluboat.springcloud.entities.CommonResult;

import javax.annotation.Resource;
import java.util.List;

//@RequestMapping(value = "/employees/{id}",method = RequestMethod.GET)

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    @PostMapping(value = "/create")
    public boolean save(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return userService.removeById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return userService.removeByIds(ids);
    }
    @GetMapping
    public List<User> findAll() {
        return userService.list();
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable Integer id) {
        return userService.getById(id);
    }
    @GetMapping("/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        User user = userService.getById(id);
        log.info("****插入结果：{payment}");
        if(user!=null){
            return new CommonResult(200,"查询成功",user);
        }else {
            return new CommonResult(444,"无记录");
        }
    }


    @GetMapping("/page")
    public Page<User> findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize) {
        return userService.page(new Page<>(pageNum,pageSize));
    }



}
