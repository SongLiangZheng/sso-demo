package com.test.slz.base.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class BaseTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseTestApplication.class, args);
    }

}
