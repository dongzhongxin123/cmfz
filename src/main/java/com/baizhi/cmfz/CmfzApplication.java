package com.baizhi.cmfz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.baizhi.cmfz.dao")
@SpringBootApplication
public class CmfzApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmfzApplication.class, args);
    }

}
