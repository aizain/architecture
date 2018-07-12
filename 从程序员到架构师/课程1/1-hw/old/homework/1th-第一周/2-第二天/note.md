# 第二天课程

> 第一周课程说明
```
今天是Java特训营开课的第一周，在此提醒大家一些需要注意的要点：
1、本周，陈老师将跟大家分享如下内容：语言环境、开发环境与生产环境。
2、今晚八点半，cctalk的直播课程，各位学员不要错过。
3、电脑配置不达标的同学尽快想办法解决。
4、课程中要多和老师互动，彼此的想法也要在群里多交流。
新的一周，祝学员们都能有所进步，希望大家都能做到今日事今日毕～有问题随时沟通
```

> 今日内容
```
今天解决java的开发 测试 与多版本jdk依赖的团队工作环境问题
```

## 1 问题回顾

> 虚拟机配网络的问题
```
可以不用桥接模式、物理网卡
使用NAT模式 DHCP

需要补充计算机网络基础 - 周六补充
```

> 常用技巧 - 尽量减少重复工作
```
把本地的公钥上传到服务器，之后就可以免密码登录
更改 /etc/hosts 文件，为特定 ip 绑定域名
可以定义别名，减少输入命令的长度 alias
```

> git - 便于管理项目 - 只要磁盘能访问就能clone
```
git init // 建立仓库 - 非裸仓库
git clone --bare demo1/ // 克隆成裸仓库
git init --bare // 建立空裸仓库
```

> 建立专职用户
```
adduser git
用git用户克隆仓库
在家目录添加.ssh，并且上传它人的公钥，这样都可以 git clone git@xxx
```

> 多程序员操作服务器
```
// 建立两个用户
adduser zhangsan
adduser lisi
ls /home

// 分别上传公钥到各自文件夹
// 将用户添加到一个用户组
```

> 管理服务器 - 软件
```
coda 软件
```

## 2 JDK

> 协议
```
gnu - 适合作为工具
绝对开源软件，商业项目不要二次加工gnu类软件，例如linux，
安卓慢，有部分原因就是无法修改linux内核

apache - 适合作为商业项目
保留原作者，可作为商业软件
java大部分都是基于apache的
```

> jdk6 jdk7 jdk8
```
67差异不大，8差异大，加入函数式编程

```

> 下载jdk
```
wget // 下载软件
wget http://download.oracle.com/otn-pub/java/jdk/8u144-b01/090f390dda5b47b9b721c7dfaa008135/jdk-8u144-linux-x64.tar.gz
```

> 安装jdk
```
解压到 /opt/java/jdk8
软连接 ln -s jdk8 jdk // 便于迁移
```

> 修改配置文件 - 用户环境变量
```
cd
vim .bashrc

###
PATH=/opt/java/jdk/bin/:$PATH
export PATH
###

source .bashrc
```

> 管理不同版本jdk
```
通过删除软连接，重新建立软连接来达到此目的
管理软件 jenv
```

> 项目管理工具
```
gredle
```