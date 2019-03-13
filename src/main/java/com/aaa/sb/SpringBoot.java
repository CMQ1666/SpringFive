package com.aaa.sb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

/**
 * className:SpringBoot
 * discription:
 * author:cmq
 * createTime:2018-11-29 18:49
 */
 @SpringBootApplication
@MapperScan("com.aaa.sb.dao") //扫描dao接口
 @Component
public class SpringBoot {
    public static void main(String[] args) {
        SpringApplication.run(SpringBoot.class);
    }
}
