# CurrentRateLimiter
CurrentLimiter(**Current** rate **limiter**)，基于Redis链表+Lua算法实现的分布式限流器，提供
- 限流功能
- 支持单机、集群

## 原理
- 利用redis List 链表结构
- 存储、操作限制条数的请求时间戳
- 通过Lua脚本和Redis单线程的特性保证操作的原子性
- 以每次lua操作中，对列表长度判断、时间戳差额判断、存储、剪切等操作达到限流作用

以Lua实现全部redis操作

## 优点
- 简单易上手
- 实现了基于时间的滑动窗口限流功能

## 当前不足
- 目前没有阻塞、延迟处理
