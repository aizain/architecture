# 第一天课程

> 第一周课程说明
```
今天是Java特训营开课的第一周，在此提醒大家一些需要注意的要点：
1、本周，陈老师将跟大家分享如下内容：语言环境、开发环境与生产环境。
2、今晚八点半，cctalk的直播课程，各位学员不要错过。
3、电脑配置不达标的同学尽快想办法解决。
4、课程中要多和老师互动，彼此的想法也要在群里多交流。
新的一周，祝学员们都能有所进步，希望大家都能做到今日事今日毕～有问题随时沟通
```

## 1.安装系统

> 镜像
```
ubuntu-16.04-server-amd64
建议LTS版
```

> 用户
```
不建议用管理员登录linux操作系统
```

> ubuntu官网
```
http://www.ubuntu.org.cn/index_kylin
http://www.ubuntu.org.cn/download/server // 一般
http://www.ubuntu.org.cn/download/cloud // 便于集群
http://www.ubuntu.org.cn/download/ubuntu-kylin // 中文版
```

> 版本
``` 
amd64 // 64位操作系统
```

> 远程连接工具
```
putty 建议官网下载，开源软件（建议官网或者百度认证的）
```

## 2.配置环境

> 安装常用软件
```
sudo apt-get update
sudo apt-get install vim
sudo apt-get install 
```

> 其他编辑器
```
nano
```

> 改主机名
```
sudo vim /etc/hostname
```

> 阿里云源地址
```
位置：
/etc/apt

更换：
deb-src http://archive.ubuntu.com/ubuntu xenial main restricted #Added by software-properties
deb http://mirrors.aliyun.com/ubuntu/ xenial main restricted
deb-src http://mirrors.aliyun.com/ubuntu/ xenial main restricted multiverse universe #Added by software-properties
deb http://mirrors.aliyun.com/ubuntu/ xenial-updates main restricted
deb-src http://mirrors.aliyun.com/ubuntu/ xenial-updates main restricted multiverse universe #Added by software-properties
deb http://mirrors.aliyun.com/ubuntu/ xenial universe
deb http://mirrors.aliyun.com/ubuntu/ xenial-updates universe
deb http://mirrors.aliyun.com/ubuntu/ xenial multiverse
deb http://mirrors.aliyun.com/ubuntu/ xenial-updates multiverse
deb http://mirrors.aliyun.com/ubuntu/ xenial-backports main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ xenial-backports main restricted universe multiverse #Added by software-properties
deb http://archive.canonical.com/ubuntu xenial partner
deb-src http://archive.canonical.com/ubuntu xenial partner
deb http://mirrors.aliyun.com/ubuntu/ xenial-security main restricted
deb-src http://mirrors.aliyun.com/ubuntu/ xenial-security main restricted multiverse universe #Added by software-properties
deb http://mirrors.aliyun.com/ubuntu/ xenial-security universe
deb http://mirrors.aliyun.com/ubuntu/ xenial-security multiverse
```

> 查看ip
```
ifconfig
```

> 配置免密码
```
ssh hadoop01 -- ssh登录需要免密码的服务器
exit -- 登出
ssh-keygen -- 创建密钥，通过RSA算法加密
ssh-copy-id root@hadoop01 -- 免密白名单加入本机ip
```

> 配置hosts
```
vim /etc/hosts
```

> 新建用户
```
adduser zain
```

> 关闭密码登录
```
rm known_hosts
vim /etc/ssh/sshd_config
修改 PasswordAuthentication no
/etc/init.d/ssh restart
```

> 修改/查看用户
```
sudo vim /etc/passwd
```

## git仓库

> 初始化仓库
```
git init
```

> 下载项目(克隆某个服务器下的内容 - 只要有访问权限)
```
git clone zain@xxxx/demo1
```

> 搭建git仓库
```
服务器：
mkdir -p /ldata/code
chown zain:zain /ldata/code
cd /ldata/code
git init
git config receive.denyCurrentBranch ignore

或者：
git --bare init
```

> 获取仓库
```
git clone zain@139.198.12.94:/ldata/code
密码：123456
```

## other
```
运维工程师常用centos，因为考redhat认证
```