package com.ml.jkeep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 启动类
 *
 * @author liangzhong
 * @version 1.0
 * @date 2019/06/19
 */
@EnableAsync
@EnableCaching
@SpringBootApplication
public class JKeepApplication {

    public static void main(String[] args) {
        SpringApplication.run(JKeepApplication.class, args);
    }

}
