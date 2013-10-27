package com.toe.jiakao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class TestJK {
	
	private String tip = "=%E6%82%A8%E6%98%AF%E6%9D%A5%E8%87%AA%E4%BA%A4%E8%AD%A6%E5%B9%B3%E5%8F%B0%E7%9A%84%E7%94%A8%E6%88%B7.";
	private String redirect_uri = "http%3A%2F%2Fwww.stc.gov.cn%3A9090%2Foauth%2Fauth%3Fsys_href_uri%3Dhttp%3A%2F%2Fcgs1.stc.gov.cn%2FLogin.aspx";
	private String sign = "7524a1c199eb4a9566a11882d7df352a272853ecd278750b53712e99af2234d65b23c01a67f9921aade54f20ef4fc368b03bb81f39b70ce81296118dd031185f7b0e740de1f95325b5b477c872e7429d49186a2396fd98ae91156e774ae06148e2ee007afce5260dd1435af3ace87e565fc9690756bbc0015bf4cee2cba80436";
	
	public static void main(String[] args) {
		new TestJK().jk();
	}

	private void jk(){
		String url = "https://auth.kftpay.com.cn/oauth/traffic";
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse rep = null;
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("client_id", "2012121100040200"));
        nvps.add(new BasicNameValuePair("grant_type", "client_credentials"));
        nvps.add(new BasicNameValuePair("user_level", "1"));
        nvps.add(new BasicNameValuePair("tip", tip));
        nvps.add(new BasicNameValuePair("redirect_uri", redirect_uri));
        nvps.add(new BasicNameValuePair("sign", sign));
        try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			rep = httpclient.execute(httpPost);
			System.out.println(rep.getStatusLine());
            HttpEntity entity = rep.getEntity();

            if (entity != null) {
                ContentType contentType = ContentType.getOrDefault(entity);
                Charset charset = contentType.getCharset();
                InputStream is = entity.getContent();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(is, charset));
                String line = null;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
                is.close();
            }
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					rep.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }
	
}
