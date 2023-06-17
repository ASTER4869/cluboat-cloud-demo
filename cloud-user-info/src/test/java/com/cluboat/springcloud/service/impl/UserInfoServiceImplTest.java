package com.cluboat.springcloud.service.impl;

import com.cluboat.springcloud.entity.UserInfoEntity;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserInfoServiceImplTest {

    @Resource
    UserInfoServiceImpl userInfoServiceImpl;

    @BeforeClass
    public static void startTest(){
        System.out.println("开始测试...");
    }

    @Test
    public void testchangeUserInfo1(){
        final UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setUserId(1);
        userInfoEntity.setUserName("123456");
        userInfoEntity.setUserSign("");
        String test = userInfoServiceImpl.changeUserInfo(userInfoEntity);
        Assert.assertEquals("用户昵称违法",test);
    }

    @Test
    public void testchangeUserInfo2(){
        final UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setUserId(1);
        userInfoEntity.setUserName("aa@");
        userInfoEntity.setUserSign("123456");
        String test = userInfoServiceImpl.changeUserInfo(userInfoEntity);
        Assert.assertEquals("用户昵称违法",test);
    }

    @Test
    public void testchangeUserInfo3(){
        final UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setUserId(1);
        userInfoEntity.setUserName("刻晴");
        userInfoEntity.setUserSign("123456");
        String test = userInfoServiceImpl.changeUserInfo(userInfoEntity);
        Assert.assertEquals("用户昵称已存在",test);
    }

    @Test
    public void testchangeUserInfo4(){
        final UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setUserId(1);
        userInfoEntity.setUserName("用户45464");
        String s = "1";
        for (int i = 0;i<200;i++)
            s = s + "1";
        userInfoEntity.setUserSign(s);
        String test = userInfoServiceImpl.changeUserInfo(userInfoEntity);
        Assert.assertEquals("个人签名超过字数",test);
    }

    @Test
    public void testchangeUserInfo5(){
        final UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setUserId(1);
        userInfoEntity.setUserName("用户1245464");
        userInfoEntity.setUserSign("123");
        String test = userInfoServiceImpl.changeUserInfo(userInfoEntity);
        Assert.assertEquals("更改成功",test);
    }




    @AfterClass
    public static void endTest(){
        System.out.println("结束测试...");
    }
}