package com.yuanqn.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author:yuanqinnan
 * @date: 2019/7/13 14:02
 */

@SpringBootApplication
@MapperScan("com.yuanqn.admin.*.mapper")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}