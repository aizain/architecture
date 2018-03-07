package io.vertx.util;


import io.vertx.util.Secret.NewMd5;

//检查签名
public class CheckToken {

  private final static int DEFAULT_DEV_NO = 3;
  private final static String[] DEFAULT_DEV = new String[DEFAULT_DEV_NO];
  private final static int DEFAULT_CRYPT_TOKEN_LEN = 8;

  static {
    DEFAULT_DEV[0] = "qdw0i9auc8shaidwbjeb32jfewcaiohubi";
    DEFAULT_DEV[1] = "uhibjnf2ihubivejnqijowuivbjnqq1ed1";
    DEFAULT_DEV[2] = "kco1903jicqoaiwhruif2n3iof18hfuibn";
  }

  public static String check(String path, String sign, String token, String guid, String param, Long timestamp){

    long millis = System.currentTimeMillis();
    Long l = millis -  timestamp;
    if (l>30000){
      return "SN002";
      //超过30分钟 时间异常
    }
    String cryptToken = CheckToken.getcryptToken(token);
    String signChecked = NewMd5.GetMD5Code(path + timestamp + guid + param + cryptToken);
    if (signChecked.equalsIgnoreCase(sign)){
      return "SN000";
      //签名认证成功
    }else {
      return "SN005";
      //签名错误
    }
  }

  public static String getcryptToken(String token){
    //各种算法  返回加密后的cryptToken
    String cryptToken = token;

    int tokenLen = token.length();
    int devIndex = tokenLen % DEFAULT_DEV_NO;
    String dev = DEFAULT_DEV[devIndex - 1];
    cryptToken = dev.substring(0, DEFAULT_CRYPT_TOKEN_LEN);

    return cryptToken ;
  }
}
