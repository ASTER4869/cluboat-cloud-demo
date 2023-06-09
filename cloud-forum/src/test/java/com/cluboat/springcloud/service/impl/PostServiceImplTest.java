package com.cluboat.springcloud.service.impl;
import com.cluboat.springcloud.entity.dto.PostListDTO;
import com.cluboat.springcloud.entity.param.PostGetByClubIdParam;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PostServiceImplTest {

    @Resource
    PostServiceImpl postServiceImpl;

    @BeforeClass
    public static void startTest(){
        System.out.println("开始测试...");
    }

    @Test
    public void testGetPostInfo1(){
        final PostGetByClubIdParam postGetByClubIdParam = new PostGetByClubIdParam();
        postGetByClubIdParam.setClubId(1);
        postGetByClubIdParam.setStatus("正常");
        List<PostListDTO> test = postServiceImpl.GetPostListByClubId(postGetByClubIdParam);
        
        Assert.assertEquals(test.get(test.size()-1).getPostTitle(),"帖子存在");
    }

    @Test
    public void testGetPostInfo2(){
        final PostGetByClubIdParam postGetByClubIdParam = new PostGetByClubIdParam();
        postGetByClubIdParam.setClubId(1);
        postGetByClubIdParam.setStatus("正常");
        List<PostListDTO> test = postServiceImpl.GetPostListByClubId(postGetByClubIdParam);

        for(int i=0;i<test.size();i++){
            System.out.println(test.get(i).getPostTitle());
        }

        System.out.println("===="+test.get(test.size()-1).getPostTitle());
        Assert.assertEquals(test.get(test.size()-1).getPostTitle(),"帖子存在");
    }


    @AfterClass
    public static void endTest(){
        System.out.println("结束测试...");
    }
}