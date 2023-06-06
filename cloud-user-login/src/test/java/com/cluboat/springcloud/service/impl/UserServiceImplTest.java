package com.cluboat.springcloud.service.impl;

import com.cluboat.springcloud.entity.UserEntity;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
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
    public void testLoginSuccess1(){
        final UserEntity userEntity1 = new UserEntity();
        userEntity1.setUserPhone("13011112222");
        userEntity1.setUserPassword("666666");
        String test = userServiceImpl.loginJudge(userEntity1);
        Assert.assertEquals(test,"登录成功");
    }

    @Test
    public void testLoginSuccess2(){
        final UserEntity userEntity1 = new UserEntity();
        userEntity1.setUserPhone("13011113333");
        userEntity1.setUserPassword("666666");
        String test = userServiceImpl.loginJudge(userEntity1);
        Assert.assertEquals(test,"账户不存在");
    }
}