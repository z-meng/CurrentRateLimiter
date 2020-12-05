package com.github.currentlimiter.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @description:
 * @Author: zuochuanmeng
 * @Mail chuanmeng_zuo@163.com
 * @Date: 2020/12/4
 */
@Slf4j
@Service
public class CurrentLimiterService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private DefaultRedisScript<Boolean> currentLimitScript;

    /**
     * redis存储的限流单位前缀
     */
    public static final String PREFIX = "pa_rate_limit.user_id_";

    /**
     * 限流实现
     *
     * @param userId 用以redis存储的KEY，可以是基于用户的userId,或者IP
     * @param limit 限流次数
     * @param interval 限流间隔 （单位毫秒）
     * @return
     */
    public boolean currentLimit(Integer userId, Integer limit, Long interval){
        String key = PREFIX + userId;
        boolean flag = redisTemplate.execute(currentLimitScript, Collections.singletonList(key), Long.toString(System.currentTimeMillis()),
                Integer.toString(limit), Long.toString(interval));
        if(!flag){
            log.error("接口调用过于频繁！");
        }
        return flag;
    }
}
