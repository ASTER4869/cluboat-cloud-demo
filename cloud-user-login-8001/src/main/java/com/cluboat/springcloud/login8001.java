package com.cluboat.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cluboat.springcloud.dao")
public class login8001 {
    public static void main(String[] args){
        SpringApplication.run(login8001.class,args);
    }
}
