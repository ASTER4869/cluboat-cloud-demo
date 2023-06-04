package com.cluboat.springcloud.service.impl;

import com.cluboat.springcloud.entity.UserEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Resource
    UserServiceImpl userServiceImpl;

    @Test
    public void testLoginSuccess(){
        final UserEntity userEntity1 = new UserEntity();
        userEntity1.setUserPhone("13011112222");
        userEntity1.setUserPassword("666666");
        String test1 = userServiceImpl.loginSuccess(userEntity1);
        Assert.assertEquals(test1,"登录成功");
    }
}