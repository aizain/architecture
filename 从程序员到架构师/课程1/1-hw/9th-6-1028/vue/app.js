var express = require('express');
var http = require('http');
var app = express();

app.use('/example', express.static(__dirname + '/example'));

http.createServer(app).listen(8081);