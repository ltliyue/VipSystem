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
	 * 请求对应的基础URL
	 */
	public static final String BASE_URL = "http://"+Util.URL+":8080/os/";
	
	/**
	 * 通过URL获取HttpGet请求
	 * @param url
	 * @return HttpGet
	 */
	private static HttpGet getHttpGet(String url){
		HttpGet httpGet = new HttpGet(url);
		return httpGet;
	}
	
	/**
	 * 通过URL获取HttpPost请求
	 * @param url
	 * @return HttpPost
	 */
	private static HttpPost getHttpPost(String url){
		HttpPost httpPost = new HttpPost(url);
		return httpPost;
	}
	
	/**
	 * 通过 HttpGet请求获取HttpResponse对象
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
	 * 通过HttpPost获取HttpPonse对象
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
	 * 将URL打包成HttpPost请求，发送，得到查询结果 网络异常 返回 "exception"
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
	 * 发送Post请求，得到查询结果 网络异常 返回 "exception"
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
	 * 将URL打包成HttpGet请求，发送，得到查询结果 网络异常 返回 "exception"
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
	 * 发送Get请求，得到查询结果 网络异常 返回 "exception"
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
