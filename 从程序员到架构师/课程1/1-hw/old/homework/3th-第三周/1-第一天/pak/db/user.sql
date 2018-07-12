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
	PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE
)
WITH (OIDS=FALSE);

