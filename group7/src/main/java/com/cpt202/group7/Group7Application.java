package com.cpt202.group7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })//加上它就解决了
public class Group7Application {

    public static void main(String[] args) {
        SpringApplication.run(Group7Application.class, args);
    }

}
