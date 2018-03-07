# 版本1-3的简单比较  

## version 1  

> 说明  

```
版本1的语法还是比较简单的，当时好像很多书都是1的简单语法
作用基本就是知名容器启动时的
image/build volumes links
```

> example
```
web:
  build: .
  ports:
   - "5000:5000"
  volumes:
   - .:/code
  links:
   - redis
redis:
  image: redis
```

## version 2

> 说明  

```md
版本2就加了很多东西
官方升级简介截取
  In the majority of cases, moving from version 1 to 2 is a very simple process:
  1. Indent the whole file by one level and put a services: key at the top.
  2. Add a version: '2' line at the top of the file.
网址：
https://docs.docker.com/compose/compose-file/compose-versioning/#version-1-to-2x

针对 build logging links等很多有引入新东西
```

> example
```
version: '2'
services:
  web:
    build: .
    ports:
     - "5000:5000"
    volumes:
     - .:/code
  redis:
    image: redis
```


## version 3  

> 说明  

```
官方说明：
Designed to be cross-compatible between Compose and the Docker Engine’s swarm mode, version 3 removes several options and adds several more.

多了个 deploy 貌似更便于和swarm一起用，部署集群了
```

> example
```
version: "3"
services:
  db:
    image: postgres
    volumes:
      - data:/var/lib/postgresql/data
volumes:
  data:
    driver: mydriver
```