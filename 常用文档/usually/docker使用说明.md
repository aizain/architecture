# docker 使用说明

### 各网址
```
dockerhub：  // docker公共镜像市场
https://hub.docker.com/explore/

阿里镜像：
https://dev.aliyun.com/search.html

官方学习文档：
https://docs.docker.com/get-started/

比较好的官方视频：
https://www.docker.com/products/docker-toolbox?spm=5176.100239.blogcont2307.10.mR4MUM

查看指令帮助：
docker -h
docker run -h
docker images -h
docker ps -h
docker exec -h
```

### docker配置
> 使用阿里镜像 mac
```
利用docker toolbox的daemon
修改 registry
https://dev.aliyun.com/
```


### 常用指令

> 查看镜像信息
```
docker images
```

> 下载镜像
```
docker pull centos
```

> 查看容器信息
```
docker ps
docker ps -a
```

> 运行容器 - 指定运行镜像
```
docker run redis
```

> 清空无用容器
```
docker rm $(docker ps -aq)
```