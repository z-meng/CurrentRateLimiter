package com.github.currentlimiter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @Author: zuochuanmeng
 * @Date: 2020/12/4
 */
@Service
public class LuaConfig {

    @Bean
    public DefaultRedisScript<Boolean> currentLimitScript() {
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/currentlimit.lua")));
        redisScript.setResultType(Boolean.class);
        return redisScript;
    }
}
