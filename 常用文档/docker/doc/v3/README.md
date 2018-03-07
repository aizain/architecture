# compose v3 介绍 
## 目录  

- [build](#build)
- [command](#command)
- [configs](#configs)

> 简述  

- The Compose file is a `YAML` file defining `services`, `networks` and `volumes`
- 总之常用命名：docker-compose.yml
- 可以利用 ${xxx} 取得系统的环境变量，如下：    
```
db:
  image: "postgres:${POSTGRES_VERSION}"
```
- 下面会更新常用到的，其他参见官网

> 官网
```
https://docs.docker.com/compose/compose-file/
阅读说明：
大写的为主要参数
小的为主要参数的附属参数

例如:
build CONTEXT DOCKERFILE
```

## build  
`用于从本地构建镜像`

> 附属参数  
```
CONTEXT DOCKERFILE ARGS CACHE_FROM LABELS
```

> 示例  
[build-指定构建](./build1/docker-compose.yml) 
[build-指定参数](./build2/docker-compose.yml) 
```js
CONTEXT DOCKERFILE 可以指定构建位置
ARGS 可以为 DOCKERFILE 中参数赋值
LABELS 设置镜像的标签 // 指定3.3即可用 This option is new in v3.3
images 可以为当前构建对象生成自定义名字/版本的镜像
```

## command  
`用于运行指令`  
```
Override the default command
```

> 示例 
[command](./command/docker-compose.yml) 

## configs  
`用于设置配置文件`  

> 简述
```
需要版本 3.3 以上
需要先部署 swarm 之后才可使用
```

```js
source 容器里的配置文件 // The name of the config as it exists in Docker.
target 挂载到容器的路径 //  The path and name of the file that will be mounted in the service’s task containers. Defaults to /<source> if not specified
uid 设置用户uid
gid 设置用户组gid
mode 设置权限
```

> 示例  
```
食用说明：

```
[command](./configs/docker-compose.yml) 

