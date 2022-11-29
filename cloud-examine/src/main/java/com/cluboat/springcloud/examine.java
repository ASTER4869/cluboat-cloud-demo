package com.cluboat.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.cluboat.springcloud.mapper")
@EnableDiscoveryClient
public class examine {

    public static void main(String[] args){
        SpringApplication.run(examine.class,args);
    }
}
