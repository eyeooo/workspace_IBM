package com.toe.jiakao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;


public class TestHttpClient {

	public static void main(String[] args) throws Exception {
//		test1();
		mulRsq();
	}

	private static void test1(){
		String url = "http://www.baidu.com";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse rep = null;
		try {
			rep = httpclient.execute(new HttpGet(url));
			System.out.println(rep.getStatusLine());
			HttpEntity entity = rep.getEntity();
			if(entity!=null){
				ContentType contentType = ContentType.get(entity);
				Charset charset = contentType.getCharset();
				InputStream in = entity.getContent();
				BufferedReader br = new BufferedReader(new InputStreamReader(in, charset));
				String line = "";
				while((line=br.readLine())!=null){
					System.out.println(line);
				}
				in.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				rep.close();
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	private static void mulRsq() throws IOException {
		int execTimes = 5;
		
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(200);
		cm.setDefaultMaxPerRoute(20);
		CloseableHttpClient httpClient = HttpClients.custom()
				.setConnectionManager(cm).build();
		
		try{
		// URIs to perform GETs on
		String[] urisToGet = { "http://www.baidu.com/",
				"http://www.google.com/", "http://www.qq.com/",
				"http://www.163.com/" };
		// create a thread for each URI
		GetThread[] threads = new GetThread[urisToGet.length];
		for (int i = 0; i < threads.length; i++) {
			HttpGet httpget = new HttpGet(urisToGet[i]);
			threads[i] = new GetThread(httpClient, httpget, execTimes, urisToGet[i]);
		}
		// start the threads
		for (int j = 0; j < threads.length; j++) {
			threads[j].start();
		}
		// join the threads
		for (int j = 0; j < threads.length; j++) {
			try {
				threads[j].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		}finally{
			httpClient.close();
		}

	}
	
}

class GetThread extends Thread {
	private final CloseableHttpClient httpClient;
	private final HttpContext context;
	private final HttpGet httpget;
	private int execTimes;
	private final String name;

	public GetThread(CloseableHttpClient httpClient, HttpGet httpget, int execTimes, String name) {
		this.httpClient = httpClient;
		this.context = HttpClientContext.create();
		this.httpget = httpget;
		this.execTimes = execTimes;
		this.name=name;
	}

	@Override
	public void run() {
		try {
			while (execTimes-- > 0) {
				CloseableHttpResponse response = httpClient.execute(httpget,
						context);
				try {
					System.out.println(name+":"+response.getStatusLine()+" #"+execTimes);
					// HttpEntity entity = response.getEntity();
				} finally {
					response.close();
				}
			}
		} catch (ClientProtocolException ex) {
			// Handle protocol errors
		} catch (IOException ex) {
			// Handle I/O errors
		}
	}
}