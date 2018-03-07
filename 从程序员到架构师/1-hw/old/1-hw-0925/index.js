/**
 * 
 * 影厅 0-15 16个厅 3种厅 票价不一样
 * 看每个影厅订票情况 订了显示红色 没订显示绿色
 * 统计销售额
 * 
 * 解决思路：
 * 1个string -> 1厅 64位 -> 给每个座位编号 -> 红0绿1
 * 遍历每一个位，01 -> 不同前端颜色
 * 
 * 
 * 解决方案：
 * 影厅座位情况 - Redis - bit - 0没人 1有人
 * Bit:cinema:seat:001 \x
 * 001 - 影厅号
 * \x - 座位情况
 * 
 * 影厅座位票情况 - Redis - 字符串 - 0有 1没有
 * Str:cinema:ticket:001:0 1
 * 001 - 影厅号
 * 0 - 第几个座位
 * 1 - 票数量
 */
const Redis = require('ioredis');
const Promise = require('bluebird');
const express = require('express');
const app = express();
app.use(express.static('public'));
app.listen(19912);
let redis = new Redis(32768)
redis.select(1);

// 初始化redis
redis.flushdb();

// 设置影厅座位情况
for (let i=0;i<16;i++) for (let j=0;j<64;j++) redis.setbit(`Bit:cinema:seat:${i}`, j, 0);
redis.keys('Bit:cinema:seat:*').tap(console.log);

// 设置影厅售卖的票
for (let i=0;i<16;i++) for (let j=0;j<64;j++) redis.set(`Str:cinema:ticket:${i}:${j}`, 1);
redis.keys('Str:cinema:ticket:*').tap(console.log);

// 买票函数 返回票id null没票了
let takeTicket = (cinema_no, seat_no) => {
  return redis.decrby(`Str:cinema:ticket:${cinema_no}:${seat_no}`, 1)
  .then(ticket_count => {
    let ticket = null;
    if (ticket_count >= 0) {
      redis.setbit(`Bit:cinema:seat:${cinema_no}`, seat_no, 1);  
      ticket = `${new Date().getDate()}:${cinema_no}:${seat_no}`;
    }
    return ticket;
  }).tap(console.log);
}

// 查看影厅座位情况
let lookSeat = (cinema_no) => redis.get(`Bit:cinema:seat:${cinema_no}`).then(v => Buffer.from(v));

// 看一下该座位的票
let lookTicket = (cinema_no, seat_no) => redis.get(`Str:cinema:ticket:${cinema_no}:${seat_no}`)

// 编码中间件
app.use((req, res, next) => {
  console.log('设置编码');
  res.setHeader('Content-Type', 'text/html;charset=utf-8');
  next();
});

// 获取某影厅某座位票的数量
app.get('/ticket/:cinema_no/:seat_no', ({ params }, res) => 
  lookTicket(params.cinema_no, params.seat_no).tap(console.log)
  .then(ticket_count => res.end(
    `影厅${params.cinema_no} 座位${params.seat_no} 剩余票 ${ticket_count} 个`)));

// 获取某影厅座位情况
app.get('/seat/:cinema_no', ({ params }, res) => 
  lookSeat(params.cinema_no).tap(console.log)
  .then(seats_buffer => {
    let ret = seats_buffer;
    console.log(ret);
    res.end(ret);
  }));

// 买票
app.get('/buy/ticket/:cinema_no/:seat_no', ({ params }, res) => 
  takeTicket(params.cinema_no, params.seat_no)
  .then(ticket => res.end(ticket ? `买票成功 ${ticket}` : '买票失败')));
 