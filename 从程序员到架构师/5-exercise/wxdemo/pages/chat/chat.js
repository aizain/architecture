/**
 * @fileOverview 聊天室综合 Demo 示例
 */


/**
 * 生成一条聊天室的消息的唯一 ID
 */
function msgUuid() {
  if (!msgUuid.next) {
    msgUuid.next = 0;
  }
  return 'msg-' + (++msgUuid.next);
}

/**
 * 生成聊天室的系统消息
 */
function createSystemMessage(content) {
  return { id: msgUuid(), type: 'system', content };
}

/**
 * 生成聊天室的聊天消息
 */
function createUserMessage(content, user, isMe) {
  return { id: msgUuid(), type: 'speak', content, user, isMe };
}

// 声明聊天室页面
Page({

  /**
   * 聊天室使用到的数据，主要是消息集合以及当前输入框的文本
   */
  data: {
    messages: [],
    inputContent: '大家好啊',
    lastMessageId: 'none',
  },

  /**
   * 页面渲染完成后，启动聊天室
   * */
  onReady() {
    wx.setNavigationBarTitle({ title: '四人聊天室' });

    if (!this.pageReady) {
      this.pageReady = true;
      this.enter();
    }
  },

  /**
   * 后续后台切换回前台的时候，也要重新启动聊天室
   */
  onShow() {
    if (this.pageReady) {
      this.enter();
    }
  },

  /**
   * 页面卸载时，退出聊天室
   */
  onUnload() {
    this.quit();
  },

  /**
   * 页面切换到后台运行时，退出聊天室
   */
  onHide() {
    this.quit();
  },

  /**
   * 启动聊天室
   */
  enter() {
    this.pushMessage(createSystemMessage('正在登录...'));

    // 如果登录过，会记录当前用户在 this.me 上
    this.connect();
  },

  /**
   * 连接到聊天室信道服务
   */
  connect() {
    this.amendMessage(createSystemMessage('正在加入群聊...'));
  },

  /**
   * 退出聊天室
   */
  quit() {
    
  },

  /**
   * 通用更新当前消息集合的方法
   */
  updateMessages(updater) {
    var messages = this.data.messages;
    updater(messages);

    this.setData({ messages });

    // 需要先更新 messagess 数据后再设置滚动位置，否则不能生效
    var lastMessageId = messages.length ? messages[messages.length - 1].id : 'none';
    this.setData({ lastMessageId });
  },

  /**
   * 追加一条消息
   */
  pushMessage(message) {
    this.updateMessages(messages => messages.push(message));
  },

  /**
   * 替换上一条消息
   */
  amendMessage(message) {
    this.updateMessages(messages => messages.splice(-1, 1, message));
  },

  /**
   * 删除上一条消息
   */
  popMessage() {
    this.updateMessages(messages => messages.pop());
  },

  /**
   * 用户输入的内容改变之后
   */
  changeInputContent(e) {
    this.setData({ inputContent: e.detail.value });
  },

  /**
   * 点击「发送」按钮，通过信道推送消息到服务器
   **/
  sendMessage(e) {
    // 信道当前不可用
    // if (!this.tunnel || !this.tunnel.isActive()) {
    //   this.pushMessage(createSystemMessage('您还没有加入群聊，请稍后重试'));
    //   if (this.tunnel.isClosed()) {
    //     this.enter();
    //   }
    //   return;
    // }
    this.pushMessage(createUserMessage('测试语句', 'zain', true));

    // setTimeout(() => {
    //   if (this.data.inputContent && this.tunnel) {
    //     this.tunnel.emit('speak', { word: this.data.inputContent });
    //     this.setData({ inputContent: '' });
    //   }
    // });
  },
});