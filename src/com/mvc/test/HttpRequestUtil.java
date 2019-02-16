package com.mvc.test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;


public class HttpRequestUtil {

    /**
     * HttpClient ģ��POST����
      * ����˵��
      * @Discription:��չ˵��
      * @param url
      * @param params
      * @return String
     */
    public static String postRequest(String url, Map<String, String> params) {
        //����HttpClient��ʵ��
        HttpClient httpClient = new HttpClient();

        //����POST������ʵ��
        PostMethod postMethod = new PostMethod(url);

        //��������ͷ��Ϣ
        postMethod.setRequestHeader("Connection", "Content-type:application/x-www-form-urlencoded");

        //��Ӳ���
        for (Map.Entry<String, String> entry : params.entrySet()) {
            postMethod.addParameter(entry.getKey(), entry.getValue());
        }

        //ʹ��ϵͳ�ṩ��Ĭ�ϵĻָ�����,�����������Դ����õ���Ĭ�ϵ����Դ�����������
        httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false);

        //���մ�����
        String result = "";
        try {
            //ִ��Http Post����
            httpClient.executeMethod(postMethod);

            //���ش�����
            InputStream inputStream = postMethod.getResponseBodyAsStream();  
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));  
            StringBuffer stringBuffer = new StringBuffer();   
            while((result = br.readLine()) != null){  
            stringBuffer .append(result );  
            }  
        } catch (HttpException e) {
            // �����������쳣��������Э�鲻�Ի��߷��ص�����������
            System.out.println("���������URL!");
            e.printStackTrace();
        } catch (IOException e) {
            // ���������쳣
            System.out.println("���������쳣!");
            e.printStackTrace();
        } finally {
            //�ͷ�����
            postMethod.releaseConnection();

            //�ر�HttpClientʵ��
            if (httpClient != null) {
                ((SimpleHttpConnectionManager) httpClient.getHttpConnectionManager()).shutdown();
                httpClient = null;
            }
        }
        System.out.println(result);
        return result;
    }

    /**
     *  HttpClient ģ��GET����
      * ����˵��
      * @Discription:��չ˵��
      * @param url
      * @param params
      * @return String
     */
    public static String getRequest(String url, Map<String, String> params) {
        //����HttpClientʵ��
        HttpClient client = new HttpClient();

        //ƴ�Ӳ���
        String paramStr = "";
        for (String key : params.keySet()) {
            paramStr = paramStr + "&" + key + "=" + params.get(key);
        }
        paramStr = paramStr.substring(1);

        //����GET������ʵ��
        GetMethod method = new GetMethod(url + "?" + paramStr);

        //���շ��ؽ��
        String result = null;
        try {
            //ִ��HTTP GET��������
            client.executeMethod(method);

            //���ش�����
            result = method.getResponseBodyAsString();
        } catch (HttpException e) {
            // �����������쳣��������Э�鲻�Ի��߷��ص�����������
            System.out.println("���������URL!");
            e.printStackTrace();
        } catch (IOException e) {
            // ���������쳣
            System.out.println("���������쳣!");
            e.printStackTrace();
        } finally {
            //�ͷ�����
            method.releaseConnection();

            //�ر�HttpClientʵ��
            if (client != null) {
                ((SimpleHttpConnectionManager) client.getHttpConnectionManager()).shutdown();
                client = null;
            }
        }
        return result;
    }
}
