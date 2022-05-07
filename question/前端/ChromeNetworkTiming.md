# Chrome network timing 分析



![img](https://shuxiaoyuan-201812.oss-cn-shanghai.aliyuncs.com/PicGo/20200914174829.png)

![img](https://shuxiaoyuan-201812.oss-cn-shanghai.aliyuncs.com/PicGo/20200914175024.png)

## Queueing（队列）

排队的时间花费。可能由于该请求被渲染引擎认为是优先级比较低的资源（图片）、服务器不可用、超过浏览器并发请求的最大连接数（Chrome的最大并发连接数是6）

【资源调度】期间，浏览器有自己的线程限制，所有的请求不可能都能够同时发送，一般chrome的最大并发连接数为6，根据请求顺序，会将所有请求加入队列中，此概念表示在队列所耗费等待的时间。

## Stalled（阻塞，卡顿）

Queueing完成后，准备发出请求 ——> 实际发出请求消耗的时间。浏览器准备要发出这个请求，但由于一些情况不能发出请求指令，例如此刻没有可复用的TCP链接，此过程不包括DNS查询、建立TCP连接等时间等。（浏览器对同一个主机域名的并发连接数有限制，因此如果当前的连接数已经超过上限，那么其余请求就会被阻塞，等待新的可用连接；）

## Proxy negotiation

与代理服务器连接的时间花费

## DNS Lookup(DNS查找)

DNS解析域名消耗的时间。可以借助DNS缓存减少该消耗

## Initial connection(初始化连接)

TCP建立连接的三次握手时间

## Request sent

发送HTTP请求的时间（从第一个字节发出前到最后一个字节发出后的时间

## TTFB(Time To First Byte)

发送请求完毕到接收请求的第一个字节的时间 影响因素：线路、服务器距离、后台服务性能，MySQL查询等

## Content Download

资源下载时间 影响因素：资源大小、是否使用缓存



https://github.com/w3c/web-performance/