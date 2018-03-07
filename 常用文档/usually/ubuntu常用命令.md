# ubuntu常用命令


> 安装htop - 人类用top
```
apt-get htop
```

> linux 命令大全
```
http://man.linuxde.net/
```

## 用户相关

### /etc/passwd

> 说明
```
存放用户
每一行代表一个账号
```

> 格式:(逼死强迫症)
```
username : password : uid    : gid : allname : homedir : shell
用户名    : 密码      : 用户ID : 组ID : 用户全名 : 主目录   : 登录shell
```

> 密码
```
以前Linux的密码直接存在该文件中，现在都存在/etc/shadow中了，
存入后者的就用x表示，如果是“!”说明此用户不能用密码登录。
这也是为什么刚装好UBUNTU时不能用ROOT账号登录的根本原因
```

> UID
```
用户识别码（ID）
当UID为0时说明其账号是管理员身份，
1~499是保留给系统使用的主要是一些系统服务，不过你用了也没有关系。
500~65535是给一般用户的
```

> GID
```
与/etc/group文件有关，用户组的ID
```

> 添加用户
```
useradd zain -g 1113 -u 1117 -m -s /bin/bash

参数：
-g, --gid GROUP               name or ID of the primary group of the new account
-u, --uid UID                 user ID of the new account
-m, --create-home             create the user's home directory
-s, --shell SHELL             login shell of the new account
```

> 修改密码
```
passwd zain
```


### /etc/group

> 说明
```
用户组
一个组中可以有多个用户，
一个用户也可以属于不同的组
用户要访问属于附加组的文件时，必须首先使用newgrp命令使自己成为所要访问的组中的成员
```

> 格式
```
groupname : password : gid : members
```

> 添加用户组
```
groupadd -g 1113 zain

参数：
-g, --gid GID                 use GID for the new group
```

> 用户组sudo权限配置
```
1.切换到root用户下

2.添加sudo文件的写权限,命令是:
chmod u+w /etc/sudoers

3.编辑sudoers文件
vim /etc/sudoers
找到这行 root ALL=(ALL) ALL,在他下面添加xxx ALL=(ALL) ALL (这里的xxx是你的用户名)

ps:这里说下你可以sudoers添加下面四行中任意一条
youuser            ALL=(ALL)                ALL
%youuser           ALL=(ALL)                ALL
youuser            ALL=(ALL)                NOPASSWD: ALL
%youuser           ALL=(ALL)                NOPASSWD: ALL

第一行:允许用户youuser执行sudo命令(需要输入密码).
第二行:允许用户组youuser里面的用户执行sudo命令(需要输入密码).
第三行:允许用户youuser执行sudo命令,并且在执行的时候不输入密码.
第四行:允许用户组youuser里面的用户执行sudo命令,并且在执行的时候不输入密码.


4.撤销sudoers文件写权限,命令:
chmod u-w /etc/sudoers
```

### /etc/shadow

> 说明
```
Unix系统最初是用明文保存密码的，后来由于安全的考虑，采用crypt()算法加密密码并存放在/etc/passwd文件;
/etc/passwd文件是所有合法用户都可访问的，大家都可互相看到密码的加密字符串，这给系统带来很大的安全威胁;
现代的Unix系统使用影子密码系统，它把密码从/etc/pa sswd文件中分离出来，真正的密码保存在/etc/shadow文件中，shadow文件只能由超级用户访问;
/etc/passwd文件中所有帐户的password域的内容为"x"，如果password域的内容为"*"，则该帐号被停用;
```

> 格式
```
username : password : last_change : min_change : max_change 
: warm : failed_expire : expiration : reserved 
```

### 远程登录

> 建立用户
```
useradd zain -g 1113 -u 1117 -m -s /bin/bash
```

> 在用户目录建立.ssh
```
cd && mkdir .ssh
```

> 建立authorized_keys,放入你的公钥
```
vim authorized_keys
```


## 文件相关

### 磁盘问题

> 统计文件夹占用大小
```
du -hs root/
```