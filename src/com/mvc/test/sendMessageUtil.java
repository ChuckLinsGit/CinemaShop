package com.mvc.test;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class sendMessageUtil {

   /**
    * 发送短信消息
     * 方法说明
     * @Discription:扩展说明
     * @param phones
     * @param content
     * @return
     * @return String
     * @throws UnsupportedEncodingException 
    */
   @SuppressWarnings("deprecation")
   public static String sendMsgByGet(String phones,String content) throws UnsupportedEncodingException{
       //短信接口URL提交地址
       String url = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";

       Map<String, String> params = new HashMap<String, String>();

       params.put("accountSid", "21ca4cc48b2f42b889114b60edc75dd8");
       
       //将短信内容进行URLEncoder编码
       params.put("smsContent", URLEncoder.encode(content,"UTF-8"));
       
       params.put("to", phones);
       params.put("timestamp", String.valueOf(new Date().getTime()));
       params.put("sig", "21ca4cc48b2f42b889114b60edc75dd8"+"b9249d5e900744e6a018c15ccb289da6"+new Date().getTime());
       params.put("respDataType", "JSON");


       return HttpRequestUtil.getRequest(url, params);
   }

   @SuppressWarnings("deprecation")
   public static String sendMsgByPost(String phones,String content) throws UnsupportedEncodingException{
       //短信接口URL提交地址
       String url = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";

       Map<String, String> params = new HashMap<String, String>();

       params.put("accountSid", "21ca4cc48b2f42b889114b60edc75dd8");
       
       //将短信内容进行URLEncoder编码
       params.put("smsContent", URLEncoder.encode(content,"UTF-8"));
       
       params.put("to", phones);
       params.put("timestamp", String.valueOf(new Date().getTime()));
       params.put("sig", "21ca4cc48b2f42b889114b60edc75dd8"+"b9249d5e900744e6a018c15ccb289da6"+new Date().getTime());
       params.put("respDataType", "JSON");


       return HttpRequestUtil.postRequest(url, params);
   }
   
   /**
    * 随机生成6位随机验证码
     * 方法说明
     * @Discription:扩展说明
     * @return
     * @return String

    */
   public static String createRandomVcode(){
       //验证码
       String vcode = "";
       for (int i = 0; i < 6; i++) {
           vcode = vcode + (int)(Math.random() * 9);
       }
       return vcode;
   }
}
