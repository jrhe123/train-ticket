package com.he.train.member.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@ComponentScan("com.he")
@MapperScan("com.he.train.member.mapper")
public class MemberApplication {


    private static final Logger LOGGER = LoggerFactory.getLogger(MemberApplication.class);


    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MemberApplication.class);
        Environment env = application.run(args).getEnvironment();

        LOGGER.info(">>>>> Start [MemberApplication]");
        LOGGER.info(">>>>> Address: http://127.0.0.1:{}{}/test", env.getProperty("server.port"), env.getProperty("server.servlet.context-path"));
    }
}
