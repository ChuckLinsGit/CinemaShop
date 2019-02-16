package com.miaodiyun.httpApiDemo;

import com.miaodiyun.httpApiDemo.common.Config;

import com.miaodiyun.httpApiDemo.common.HttpUtil;


public class AccountInfo
{
	private static String operation = "/query/accountInfo";

	private static String accountSid = Config.ACCOUNT_SID;

	
	public static void execute()
	{
		String url = Config.BASE_URL + operation;
		String body = "accountSid=" + accountSid + HttpUtil.createCommonParam();

	
		String result = HttpUtil.post(url, body);
		System.out.println("result:" + System.lineSeparator() + result);
	}
}
