package com.cluboat.springcloud;

<<<<<<< Updated upstream
import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
=======
>>>>>>> Stashed changes
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.cluboat.springcloud.mapper")
@EnableDiscoveryClient
<<<<<<< Updated upstream
@EnableMPP
=======
>>>>>>> Stashed changes
public class examine {

    public static void main(String[] args){
        SpringApplication.run(examine.class,args);
    }
}
