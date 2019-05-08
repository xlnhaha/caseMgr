package com.lmx.casemgr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lmx.casemgr.mapper")
public class CasemgrApplication {

    public static void main(String[] args) {
        SpringApplication.run(CasemgrApplication.class, args);
    }

}
