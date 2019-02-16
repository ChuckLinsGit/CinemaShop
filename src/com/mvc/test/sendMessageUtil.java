package com.mvc.test;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class sendMessageUtil {

   /**
    * ���Ͷ�����Ϣ
     * ����˵��
     * @Discription:��չ˵��
     * @param phones
     * @param content
     * @return
     * @return String
     * @throws UnsupportedEncodingException 
    */
   @SuppressWarnings("deprecation")
   public static String sendMsgByGet(String phones,String content) throws UnsupportedEncodingException{
       //���Žӿ�URL�ύ��ַ
       String url = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";

       Map<String, String> params = new HashMap<String, String>();

       params.put("accountSid", "21ca4cc48b2f42b889114b60edc75dd8");
       
       //���������ݽ���URLEncoder����
       params.put("smsContent", URLEncoder.encode(content,"UTF-8"));
       
       params.put("to", phones);
       params.put("timestamp", String.valueOf(new Date().getTime()));
       params.put("sig", "21ca4cc48b2f42b889114b60edc75dd8"+"b9249d5e900744e6a018c15ccb289da6"+new Date().getTime());
       params.put("respDataType", "JSON");


       return HttpRequestUtil.getRequest(url, params);
   }

   @SuppressWarnings("deprecation")
   public static String sendMsgByPost(String phones,String content) throws UnsupportedEncodingException{
       //���Žӿ�URL�ύ��ַ
       String url = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";

       Map<String, String> params = new HashMap<String, String>();

       params.put("accountSid", "21ca4cc48b2f42b889114b60edc75dd8");
       
       //���������ݽ���URLEncoder����
       params.put("smsContent", URLEncoder.encode(content,"UTF-8"));
       
       params.put("to", phones);
       params.put("timestamp", String.valueOf(new Date().getTime()));
       params.put("sig", "21ca4cc48b2f42b889114b60edc75dd8"+"b9249d5e900744e6a018c15ccb289da6"+new Date().getTime());
       params.put("respDataType", "JSON");


       return HttpRequestUtil.postRequest(url, params);
   }
   
   /**
    * �������6λ�����֤��
     * ����˵��
     * @Discription:��չ˵��
     * @return
     * @return String

    */
   public static String createRandomVcode(){
       //��֤��
       String vcode = "";
       for (int i = 0; i < 6; i++) {
           vcode = vcode + (int)(Math.random() * 9);
       }
       return vcode;
   }
}
