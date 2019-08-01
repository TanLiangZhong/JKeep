package com.ml.jkeep.configuration;

import java.util.concurrent.ThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 *
 * Date: 2019/8/1-10:23
 * @author mengh
 * Description: 线程池的配置
 */
@Slf4j
@Configuration
@EnableAsync
public class ExecutorConfig {

    /**
     * 核心线程数，默认为1
     */
    @Value("${jkeep.threadPool.corePoolSize}")
    private Integer corePoolSize;

    /**
     * 最大线程数，默认为Integer.MAX_VALUE
     */
    @Value("${jkeep.threadPool.maxPoolSize}")
    private Integer maxPoolSize;

    /**
     * 队列最大长度 , 默认为Integer.MAX_VALUE
     */
    @Value("${jkeep.threadPool.queueCapacity}")
    private Integer queueCapacity;

    /**
     * 线程池维护线程所允许的空闲时间，默认为60s
     */
    @Value("${jkeep.threadPool.keepAliveSeconds}")
    private Integer keepAliveSeconds;

    @Bean(name="jkeepThreadPoolTaskExecutor")
    public ThreadPoolTaskExecutor asyncServiceExecutor() {
        log.info("Start config asyncServiceExecutor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix("#####Meng Thread->");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        log.info("End config asyncServiceExecutor");
        return executor;
    }

}
