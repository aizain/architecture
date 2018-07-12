import urllib
import re

def getHtml(url):
    page = urllib.urlopen(url)
    html = page.read()
    return html

def save(filename, contents):
    fh = open(filename, 'w')
    fh.write(contents)
    fh.close

html = getHtml("http://youku.com")
save('./wzh-王子豪/1-hw-0918/html/index.html', html)