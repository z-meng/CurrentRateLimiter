package com.github.currentlimiter.controller;

import com.github.currentlimiter.common.ApiResultBean;
import com.github.currentlimiter.service.CurrentLimiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @Author: zuochuanmeng
 * @Mail chuanmeng_zuo@163.com
 * @Date: 2020/12/4
 */
@RestController
@RequestMapping("/current/limit")
public class CurrentRateLimitController {
    @Autowired
    private CurrentLimiterService currentLimiterService;

    /**
     * 测试入口
     * @return
     */
    @GetMapping("/test")
    public ApiResultBean everyRequest(){
        Integer userId = 9527;
        Integer limit = 5;
        Long interval = 1000L;
        boolean b = currentLimiterService.currentLimit(userId, limit, interval);
        if(!b){
            return ApiResultBean.errorResult(442,"接口调用过于频繁！");
        }
        return ApiResultBean.successResult();
    }
}
