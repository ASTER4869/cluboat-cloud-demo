package com.cluboat.springcloud;


import com.cluboat.springcloud.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MyBatisplustest {


    @Autowired(required = false)
    private UserMapper usermapper;


    @Test
    public void testSelectList() {
        usermapper.selectList(null).forEach(System.out::println);
    }

}
