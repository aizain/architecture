import requests
import time
from parse import *
import re

def save(filename, contents):
    fh=open(filename, 'w')
    fh.write(contents) 
    fh.close

def setHead(session, headers):
    session.headers.update(headers)
    return session

def setCookie(session, dict):
    cookies = requests.utils.cookiejar_from_dict(dict, cookiejar=None, overwrite=True)
    session.cookies = cookies
    return session

def getPage(session, url):
    return session.get(url)

def postData():
    print('postData')

def main():
    url = 'http://account.youku.com'
    s = requests.session()
    h = {
      'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8'
    , 'Accept-Encoding': 'gzip, deflate, br'
    , 'Accept-Language': 'zh-CN,zh;q=0.8,en;q=0.6'
    , 'Cache-Control': 'max-age=0'
    , 'Connection': 'keep-alive'
    , 'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36'
    }
    cookie_dict = {
      'P_ck_ctl': 'A99D06F82D36587388023B92154FDC04'
    }
    s=setHead(s, h)
    s=setCookie(s, cookie_dict)
    r=getPage(s, 'http://www.youku.com')
    r.encoding='utf-8'
    print(r.text)
    save('./wzh-王子豪/1-hw-0918/html/index2.html', r.text)

main()