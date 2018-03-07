# ubuntu相关命令


## 1 网络相关

### 1.1 端口相关
> 查看已经连接的服务端口（ESTABLISHED）
```
netstat -a
```

> 查看所有的服务端口（LISTEN，ESTABLISHED）
```
netstat -ap
```

> 查看指定端口
netstat -ap | grep 8080
