# R6-Helper
- 这是一个 彩虹六号：围攻战绩查询的酷Q， QQ 机器人插件。

- 基于 酷Q、cqhttp、SpringBoot、反向websocket 的 QQ 机器人框架

- 基于Spring-CQ自定义的酷Q机器人插件

- 能够实现QQ号与彩虹六号ID的绑定，免去重复输入游戏ID的痛苦

- 数据持久化（就是以一个文件的形式存储，这里没有用数据库要不然不易于移植）

- 数据来源 https://r6stats.com

- 参考于 https://github.com/lz1998/spring-cq

- 更多功能，敬请期待。。。。

  > 本人实在看不懂C++ 只好这样曲线救国，有机会一定做一个C++版本的
  >
  > 那样的应该会更加轻量更容易安装

## 演示

```
命令 #菜单
```

![](https://raw.githubusercontent.com/PasserByJia/image-host/master/img/20200510193948.png)

```
命令 #绑定账号 ID
```

![](https://raw.githubusercontent.com/PasserByJia/image-host/master/img/20200510194939.png)

```
命令 #我的战绩
```

![](https://raw.githubusercontent.com/PasserByJia/image-host/master/img/20200510194339.png)

```
命令 #R6战绩 ID
```

![](https://raw.githubusercontent.com/PasserByJia/image-host/master/img/20200510194449.png)

# 环境准备

## 本地环境要有 JDK(JRE)

这个百度百度吧，下载jdk（版本为1.8）——安装，并配置path环境变量

## Windows运行酷Q和cqhttp

1. 准备酷Q Air

   - 方案一：下载已经配置好cqhttp的[酷Q Air](http://cq.lz1998.xin/CQA.zip)

   - 方案二：自己配置

     1. 下载[酷Q Air](https://cqp.cc/t/23253)

     2. 下载[CQHTTP插件](https://github.com/richardchien/coolq-http-api/releases)

     3. 创建文件

        ```
        酷Q Air\data\app\io.github.richardchien.coolqhttpapi\config.ini
        ```

        ```
        [general]
        use_http=false
        use_ws_reverse=true
        ws_reverse_url=ws://127.0.0.1:9011/ws/cq/
        ws_reverse_use_universal_client=true
        enable_heartbeat=true
        heartbeat_interval=60000
        ```

2. 解压后运行 CQA.exe 登录QQ账号

如果需要[酷Q Pro](http://dlsec.cqp.me/cqp-tuling)，下载解压后替换exe文件，其他不需要动

# 运行插件

## 运行酷Q并启用CQHTTP插件

![](https://raw.githubusercontent.com/PasserByJia/image-host/master/img/20200510193408.png)

## 运行打包好的jar包 

双击jar包或者，执行命令

```
java -jar CQ-R6-helper-0.0.1.jar
```

jar包下载地址 https://github.com/PasserByJia/CQ-R6-Hepler/releases/tag/0.0.1

