# mongodb常用命令


## 登录说明
> 用户密码登录
```
mongo ip:port/db -u xxx -p xxx

参数：
mongo [options] [db address] [file names (ending in .js)]

db address can be:
  foo                   foo database on local machine
  192.168.0.5/foo       foo database on 192.168.0.5 machine
  192.168.0.5:9999/foo  foo database on 192.168.0.5 machine on port 9999

Authentication Options:
  -u [ --username ] arg               username for authentication
  -p [ --password ] arg               password for authentication
```

> 登录说明
```
如果开启登录验证，需要指定用户密码登录
如果绑定了登录ip和端口，需要通过指定ip端口登录
```


## 登陆后，常用命令
> 查看mongo有哪些用户及角色
```
use admin
db.system.users.find()
```

> 修改密码
```
db.changeUserPassword('user','pwd');
```