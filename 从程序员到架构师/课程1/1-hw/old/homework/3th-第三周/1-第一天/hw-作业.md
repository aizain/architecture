# 第一天作业

## 登录注册
```
三个Verticle已部署完成
能够实现简单的登录注册
```

# 表设计
> 宽表
```
CREATE TABLE "public"."user" (
	"id" uuid NOT NULL,
	"ip" varchar(30),
	"address" varchar(50),
	"account" varchar(20),
	"password" varchar(20),
	"r_types" varchar(10),
	"r_time" timestamptz,
	"phone" varchar(20),
	"nick" varchar(20),
	"name" varchar(20),
	"qq" varchar(20),
	"wechat" varchar(20),
	"member_types" varchar(5),
	"member_level" int4,
	"experience" int8,
	"points" int8,
	"last_ip" varchar(30),
	"role" varchar(10),
	"power" varchar(10),
	PRIMARY KEY ("id")
)
```

> 垂直分表
```
用户注册：
CREATE TABLE "public"."user" (
	"id" uuid NOT NULL,
	"account" varchar(20),
	"password" varchar(20),
	PRIMARY KEY ("id")
)

用户信息：
CREATE TABLE "public"."user" (
	"id" uuid NOT NULL,
  "acct_id" uuid NOT NULL,
	"address" varchar(50),
	"r_types" varchar(10),
	"r_time" timestamptz,
	"phone" varchar(20),
	"nick" varchar(20),
	"name" varchar(20),
	"qq" varchar(20),
	"wechat" varchar(20),
	"experience" int8,
	"points" int8,
	"last_ip" varchar(30),
	"role" varchar(10),
	"power" varchar(10),
	PRIMARY KEY ("id")
)

会员信息：
CREATE TABLE "public"."user" (
	"id" uuid NOT NULL,
  "acct_id" uuid NOT NULL,
	"member_types" varchar(5),
	"member_level" int4,
	PRIMARY KEY ("id")
)

支付信息：
CREATE TABLE "card" (
"id" uuid NOT NULL,
"acct_id" uuid NOT NULL,
"gate" varchar(10) NOT NULL,
"no" varchar(100) NOT NULL,
"name" varchar(20) NOT NULL,
"bank" varchar(200) NOT NULL DEFAULT '',
"pin" varchar(10) NOT NULL DEFAULT '',
"updated" date,
"created" date,
"status" varchar(10) NOT NULL DEFAULT '',
PRIMARY KEY ("acct_id", "gate", "no")
)
```

