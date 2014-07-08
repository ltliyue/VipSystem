package com.meyao.book.util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class OrderHttpUtil {

	/**
	 * �����Ӧ�Ļ���URL
	 */
	public static final String BASE_URL = "http://"+Util.URL+":8080/os/";
	
	/**
	 * ͨ��URL��ȡHttpGet����
	 * @param url
	 * @return HttpGet
	 */
	private static HttpGet getHttpGet(String url){
		HttpGet httpGet = new HttpGet(url);
		return httpGet;
	}
	
	/**
	 * ͨ��URL��ȡHttpPost����
	 * @param url
	 * @return HttpPost
	 */
	private static HttpPost getHttpPost(String url){
		HttpPost httpPost = new HttpPost(url);
		return httpPost;
	}
	
	/**
	 * ͨ�� HttpGet�����ȡHttpResponse����
	 * @param httpGet
	 * @return HttpResponse
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private static HttpResponse getHttpResponse(HttpGet httpGet) throws ClientProtocolException, IOException{
		HttpResponse response = new DefaultHttpClient().execute(httpGet);
		return response;
	}
	
	/**
	 * ͨ��HttpPost��ȡHttpPonse����
	 * @param HttpPost
	 * @return httpPost
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private static HttpResponse getHttpResponse(HttpPost httpPost) throws ClientProtocolException, IOException{
		HttpResponse response = new DefaultHttpClient().execute(httpPost);
		return response;
	}
	
	/**
	 * ��URL�����HttpPost���󣬷��ͣ��õ���ѯ��� �����쳣 ���� "exception"
	 * @param url
	 * @return resultString 
	 */
	public static String getHttpPostResultForUrl(String url){
		System.out.println("url==="+url);
		HttpPost httpPost = getHttpPost(url);
		String resultString = null;
		
		try {
			HttpResponse response = getHttpResponse(httpPost);
			
			if(response.getStatusLine().getStatusCode() == 200)
				resultString = EntityUtils.toString(response.getEntity());				
		} catch (ClientProtocolException e) {
			resultString = "exception";
			e.printStackTrace();
		} catch (IOException e) {
			resultString = "exception";
			e.printStackTrace();
		}
		
		return resultString;
	}
	
	/**
	 * ����Post���󣬵õ���ѯ��� �����쳣 ���� "exception"
	 * @param url
	 * @return resultString
	 */
	public static String getHttpPostResultForRequest(HttpPost httpPost){
		String resultString = null;
		
		try {
			HttpResponse response = getHttpResponse(httpPost);
			
			if(response.getStatusLine().getStatusCode() == 200)
				resultString = EntityUtils.toString(response.getEntity());				
			
		} catch (ClientProtocolException e) {
			resultString = "exception";
			e.printStackTrace();
		} catch (IOException e) {
			resultString = "exception";
			e.printStackTrace();
		}
		
		return resultString;
	}
	
	/**
	 * ��URL�����HttpGet���󣬷��ͣ��õ���ѯ��� �����쳣 ���� "exception"
	 * @param url
	 * @return resultString
	 */
	public static String getHttpGetResultForUrl(String url){
		
		HttpGet httpGet = getHttpGet(url);
		String resultString = null;
		
		try {
			HttpResponse response = getHttpResponse(httpGet);
			if(response.getStatusLine().getStatusCode() == 200)
				resultString = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			resultString = "exception";
			e.printStackTrace();
		} catch (IOException e) {
			resultString = "exception";
			e.printStackTrace();
		}
		
		return resultString;
	}
	
	/**
	 * ����Get���󣬵õ���ѯ��� �����쳣 ���� "exception"
	 * @param url
	 * @return resultString
	 */
	public static String getHttpGetResultForRequest(HttpGet httpGet){
		String resultString = null;
		try {
			HttpResponse response = getHttpResponse(httpGet);
			if(response.getStatusLine().getStatusCode() == 200)
				resultString = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			resultString = "exception";
			e.printStackTrace();
		} catch (IOException e) {
			resultString = "exception";
			e.printStackTrace();
		}
		
		return resultString;
	}

}
