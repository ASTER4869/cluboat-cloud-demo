package com.cluboat.springcloud.service.impl;
import com.cluboat.springcloud.entity.PostEntity;
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

//    @Test
//    public void testGetPostInfo1(){
//        final PostGetByClubIdParam postGetByClubIdParam = new PostGetByClubIdParam();
//        postGetByClubIdParam.setClubId(1);
//        postGetByClubIdParam.setStatus("正常");
//        List<PostListDTO> test = postServiceImpl.GetPostListByClubId(postGetByClubIdParam);
//
//        Assert.assertEquals("帖子存在",test.get(test.size()-1).getPostTitle());
//    }
//
//    @Test
//    public void testGetPostInfo2(){
//        final PostGetByClubIdParam postGetByClubIdParam = new PostGetByClubIdParam();
//        postGetByClubIdParam.setClubId(666);
//        postGetByClubIdParam.setStatus("正常");
//        List<PostListDTO> test = postServiceImpl.GetPostListByClubId(postGetByClubIdParam);
//        System.out.println("===="+test.get(test.size()-1).getPostTitle());
//        Assert.assertEquals("查询社团不存在",test.get(test.size()-1).getPostTitle());
//    }
//
//    @Test
//    public void testGetPostInfo3(){
//        final PostGetByClubIdParam postGetByClubIdParam = new PostGetByClubIdParam();
//        postGetByClubIdParam.setClubId(1);
//        postGetByClubIdParam.setStatus("系统判定违规");
//        List<PostListDTO> test = postServiceImpl.GetPostListByClubId(postGetByClubIdParam);
//        System.out.println("===="+test.get(test.size()-1).getPostTitle());
//        Assert.assertEquals("社团不存在满足查询条件的帖子",test.get(test.size()-1).getPostTitle());
//    }

    @Test
    public void testAddPost1(){
        final PostEntity postEntity = new PostEntity();
        postEntity.setClubId(1);
        postEntity.setUserId(2);
        postEntity.setPostTitle("");
        String test = postServiceImpl.addPost(postEntity);
        Assert.assertEquals("帖子标题包含非法字符",test);
    }

    @Test
    public void testAddPost2(){
        final PostEntity postEntity = new PostEntity();
        postEntity.setClubId(1);
        postEntity.setUserId(2);
        postEntity.setPostTitle("@@@");
        String test = postServiceImpl.addPost(postEntity);
        Assert.assertEquals("帖子标题包含非法字符",test);
    }

    @Test
    public void testAddPost3(){
        final PostEntity postEntity = new PostEntity();
        postEntity.setClubId(1);
        postEntity.setUserId(666);
        String test = postServiceImpl.addPost(postEntity);
        Assert.assertEquals("用户不存在",test);
    }

    @Test
    public void testAddPost4(){
        final PostEntity postEntity = new PostEntity();
        postEntity.setClubId(1266);
        postEntity.setUserId(1);
        String test = postServiceImpl.addPost(postEntity);
        Assert.assertEquals("帖子所属社团不存在",test);
    }

    @Test
    public void testAddPost5(){
        final PostEntity postEntity = new PostEntity();
        postEntity.setClubId(1);
        postEntity.setUserId(1);
        postEntity.setPostTitle("11");
        String test = postServiceImpl.addPost(postEntity);
        Assert.assertEquals("创建成功",test);
    }



    @AfterClass
    public static void endTest(){
        System.out.println("结束测试...");
    }
}