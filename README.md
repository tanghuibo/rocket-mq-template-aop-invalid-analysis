# rocketMqTemplate aop 失效分析

## 项目启动

### rocketMq安装

#### 1.  安装&启动 NameServer

```bash
docker run -d -p 9876:9876 --name rmqserver  foxiswho/rocketmq:server-4.5.1
```

#### 2. 安装&启动 Broker

```bash
docker run -d -p 10911:10911 -p 10909:10909\
 --name rmqbroker --link rmqserver:namesrv\
 -e "NAMESRV_ADDR=namesrv:9876" -e "JAVA_OPTS=-Duser.home=/opt"\
 -e "JAVA_OPT_EXT=-server -Xms128m -Xmx128m"\
 foxiswho/rocketmq:broker-4.5.1
```

#### 3. 安装 RocketmqConsole

```bash
docker run -d --name rmqconsole -p 8180:8080 --link rmqserver:namesrv\
 -e "JAVA_OPTS=-Drocketmq.namesrv.addr=namesrv:9876\
 -Dcom.rocketmq.sendMessageWithVIPChannel=false"\
 -t styletang/rocketmq-console-ng
```

#### 4. 查看 rocketMq 是否安装成功

[http://127.0.0.1:8180](http://127.0.0.1:8180)