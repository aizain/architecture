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
各位学员晚上好：
1、今晚八点半的直播课程，请准时参加，不要错过；
2、直播课课间休息的时长为15分钟左右，请不要错过第二堂课；
3、观看直播课程时请着重记录自己没有跟上的部分，看回放时重点回顾；
4、明天的课程预告：
（1）    程序员必备的网络知识（根据大家作业情况反馈补充的内容）；
（2）    jvm环境下的非java语言编程；
（3）    开发生态。
5、参加明天线下课程的学员，地点在中关村创业大街怡仁咖啡一层，明天我会再发定位和图片，可以带上电脑在现场操作；
6、课程不能及时参加的，一定要跟我请假，没有交作业的学员请尽快提交。
```

## 注！
```
看到 'permission denied' 类似字眼，记得加sudo
```

## 安装环境工具

> SDKMAN 安装
```
管理 JVM 复杂环境的工具
官网：sdkman.io
安装：
curl -s "https://get.sdkman.io" | bash
使用时需要联网
```

> mac 管理软件
```
brew
```

> 安装 gradle
```
sdk install gradle
```

> 安装 node
```
wget https://nodejs.org/dist/v6.11.2/node-v6.11.2.tar.gz
apt-get install gcc g++ make // 编译软件用到的
apt-get install python
cd node-v6.11.2
./configure
make && make install
```

> vert.x
```
一整套规范
非阻塞风格
```

> 编程文档软件
```
dash
```

> 安装npm
```
简单版：
sudo apt-get install npm
源码版：
wget https://npmjs.org/install.sh --no-check-certificate
chmod 777 install.sh
sudo ./install.sh
```

> 安装nodejs版本管理工具
```
npm i n -g
```

> 安装 gulp
```
npm install gulp -g
```

> 安装 package.json里全部的 devDependencies
```
npm i
```

> 编译源文件位置
```
sassPath: './resources/sass',
libPath: './node_modules'

config.libPath + '/bootstrap/dist/js/bootstrap.min.js'
config.libPath + '/jquery/dist/jquery.min.js'
config.bowerDir + '/font-awesome/fonts/**.*'
config.sassPath + '/style.scss'
```

> bootstrap下载
```
来源1：(bootstrap3 迁移sass文件)
wget https://github.com/twbs/bootstrap-sass/archive/v3.3.7.tar.gz

来源2：
npm i bootstrap@4.0.0-beta --save-dev

官网：
https://getbootstrap.com
```

> /resources/sass/ 内容来源
```
mkdir -p ./resources/sass/
mv ./bootstrap/scss/* ./resources/sass/*
cp bootstrap.scss style.scss
```

> npm安装说明
```
开发调试用包：
npm i xxx --save-dev

peer依赖包：
npm i xxx --save-peer
```