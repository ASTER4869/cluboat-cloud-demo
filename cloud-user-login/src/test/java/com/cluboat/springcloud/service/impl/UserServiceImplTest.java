package com.cluboat.springcloud.service.impl;

import com.cluboat.springcloud.entity.UserEntity;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.junit.*;
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

    @BeforeClass
    public static void startTest(){
        System.out.println("开始测试...");
    }

    @Test
    public void testLoginSuccess1(){
        final UserEntity userEntity = new UserEntity();
        userEntity.setUserPhone("");
        userEntity.setUserPassword("123456");
        String test = userServiceImpl.loginJudge(userEntity);
        Assert.assertEquals("账号不存在",test);
    }

    @Test
    public void testLoginSuccess2(){
        final UserEntity userEntity = new UserEntity();
        userEntity.setUserPhone("13011112222");
        userEntity.setUserPassword("");
        String test = userServiceImpl.loginJudge(userEntity);
        Assert.assertEquals("密码不正确",test);
    }

    @Test
    public void testLoginSuccess3(){
        final UserEntity userEntity = new UserEntity();
        userEntity.setUserPhone("13011112222");
        userEntity.setUserPassword("123456");
        String test = userServiceImpl.loginJudge(userEntity);
        Assert.assertEquals("密码不正确",test);
    }

    @Test
    public void testLoginSuccess4(){
        final UserEntity userEntity = new UserEntity();
        userEntity.setUserPhone("13911112222");
        userEntity.setUserPassword("123456");
        String test = userServiceImpl.loginJudge(userEntity);
        Assert.assertEquals("账号不存在",test);
    }

    @Test
    public void testLoginSuccess5(){
        final UserEntity userEntity = new UserEntity();
        userEntity.setUserPhone("13011112222");
        userEntity.setUserPassword("666666");
        String test = userServiceImpl.loginJudge(userEntity);
        Assert.assertEquals("登录成功",test);
    }

    @Test
    public void testSignUpSuccess1(){
        final UserEntity userEntity = new UserEntity();
        userEntity.setUserPhone("");
        userEntity.setUserPassword("123456");
        String test = userServiceImpl.signUpJudge(userEntity);
          Assert.assertEquals("手机号为空",test);
    }

    @Test
    public void testSignUpSuccess2(){
        final UserEntity userEntity = new UserEntity();
        userEntity.setUserPhone("13911112222");
        userEntity.setUserPassword("");
        String test = userServiceImpl.signUpJudge(userEntity);
        Assert.assertEquals("密码为空",test);
    }

    @Test
    public void testSignUpSuccess3(){
        final UserEntity userEntity = new UserEntity();
        userEntity.setUserPhone("1231321");
        userEntity.setUserPassword("123456");
        String test = userServiceImpl.signUpJudge(userEntity);
        Assert.assertEquals("手机不合法",test);
    }

    @Test
    public void testSignUpSuccess4(){
        final UserEntity userEntity = new UserEntity();
        userEntity.setUserPhone("13911112222");
        userEntity.setUserPassword("1 2 3");
        String test = userServiceImpl.signUpJudge(userEntity);
        Assert.assertEquals("密码不合法",test);
    }

    @Test
    public void testSignUpSuccess5(){
        final UserEntity userEntity = new UserEntity();
        userEntity.setUserPhone("13011112222");
        userEntity.setUserPassword("1242452");
        String test = userServiceImpl.signUpJudge(userEntity);
        Assert.assertEquals("手机已注册",test);
    }

    @Test
    public void testSignUpSuccess6(){
        final UserEntity userEntity = new UserEntity();
        userEntity.setUserPhone("13911112222");
        userEntity.setUserPassword("1242452");
        String test = userServiceImpl.signUpJudge(userEntity);
        Assert.assertEquals("注册成功",test);
    }

    @AfterClass
    public static void endTest(){
        System.out.println("结束测试...");
    }
}