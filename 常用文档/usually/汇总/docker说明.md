# docker 安装说明

> docker 官网
```
官网：https://www.docker.com/
可以从Get Docker下载docker
目前docker-ce是免费版
docker-compose 

曾经开源位置：
https://github.com/moby/moby
因为商业问题改名了
```

> docker-ce ubuntu安装
```
官方教程：
https://docs.docker.com/engine/installation/linux/docker-ce/ubuntu/
```

> 简单安装：
1. Uninstall old versions
```
$ sudo apt-get remove docker docker-engine docker.io
```

2. Update the apt package index：
```
$ sudo apt-get update
```

3. Install packages to allow apt to use a repository over HTTPS：
```
$ sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    software-properties-common
```

4. Add Docker’s official GPG key：
```
$ curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
```

5. Use the following command to set up the stable repository
```
$ sudo add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"
```

6. INSTALL DOCKER CE
```
$ sudo apt-get update
$ sudo apt-get install docker-ce
```

> docker-compose 安装
``` 
官方说明：
https://docs.docker.com/compose/install/#install-compose-on-linux-systems
注：
docker for mac自带这个
```


# docker 使用说明

### 各网址
```
dockerhub：  // docker公共镜像市场
https://hub.docker.com/explore/

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
