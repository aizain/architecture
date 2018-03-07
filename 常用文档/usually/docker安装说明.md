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