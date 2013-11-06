package jni;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class DecodingValidataCode {
 
	private static String path="D:\\javaLD\\workspace_IBM\\HttpClient1\\bin\\jni\\";
	
	static	String libPath = path+"ys.lib";
	static	String image = path+"ys.gif";
	
	static {
		System.load(path+"jni_DecodingValidataCode.dll");
		System.load(path+"ys.dll");
		System.load(path+"pic.dll");
 	}
 
	public static native void hello(String msg);
  
	public static native String GetCodeFromFile(String imagePath, String libPath);
	public static native String GetCodeFromBuffer(byte[] buf, long buf_len, String libPath);
  
	public static void main(String[] args) throws IOException {
    
//		byte[] buf = FileUtils.readFileToByteArray(new File(image));
		
		InputStream is = getValidCode();
		byte[] buf = IOUtils.toByteArray(is);
		saveImage(buf);
		
//	    hello("Hello, Money!");
	  
//		String s = GetCodeFromFile(image, libPath);
//	  	System.out.println(s);
		String s1 = GetCodeFromBuffer(buf, buf.length, libPath);
		System.out.println(s1);
    
  	}
  
	private static InputStream getValidCode(){
		InputStream is = null;
		String url = "http://cgs1.stc.gov.cn/ValidteCode_SANXUE.aspx";
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse rep = null;
        try {
			rep = httpclient.execute(httpGet);
			System.out.println(rep.getStatusLine());
            HttpEntity entity = rep.getEntity();

            if (entity != null) {
                is = entity.getContent();
                return is;
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
			return null;
        }
	
	private static void saveImage(byte[] b){
		try {
		FileOutputStream fos=new FileOutputStream("c:\\vcode.gif");
        fos.write(b,0,b.length);
        fos.flush();
        fos.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
  
}

