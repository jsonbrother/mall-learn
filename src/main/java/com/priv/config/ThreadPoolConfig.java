package com.priv.config;

import com.priv.common.OverrideThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Json
 * @date 2021/6/8 10:57
 */
@Configuration
public class ThreadPoolConfig {

    /**
     * 核心线程数
     * 会一直存活 即使没有任务 线程池也会维护线程的最少数量
     */
    private static final int CORE_POOL_SIZE = 6;

    /**
     * 线程池维护线程的最大数量
     */
    private static final int MAX_POOL_SIZE = 10;

    /**
     * 线程缓冲任务队列最大容量
     */
    private static final int QUEUE_CAPACITY = 100;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    private static final long KEEP_ALIVE_TIME = 1L;


    @Bean
    public ThreadPoolExecutor customExecutor() {
        return new OverrideThreadPoolExecutor(CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new CustomizableThreadFactory("CustomExecutor-"),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

}
