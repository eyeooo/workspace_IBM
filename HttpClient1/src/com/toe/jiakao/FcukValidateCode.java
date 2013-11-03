package com.toe.jiakao;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.stream.FileImageInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.pointers.Pointer;
import org.xvolks.jnative.pointers.memory.MemoryBlockFactory;

public class FcukValidateCode {
	
	static String path ="D:\\javaLD\\workspace_IBM\\HttpClient1\\bin\\";
	public static void main(String[] args) throws Exception {
		InputStream is = getValidCode();
		getCodeFormFile(is);
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
                FileOutputStream fos=new FileOutputStream("c:\\vcode.gif");
                int data=is.read();
                while(data!=-1){
                    fos.write(data);
                    data=is.read();
                }
                fos.close();
                is.close();
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
	
	private static boolean getCodeFormFile(InputStream is)throws Exception{
		Boolean result  = false;
		String Dellpath =path+ "ys.dll";
		String Libpath=path+ "ys.lib";
		String image =path+"ys.GIF";
//		System.load(path+"Pic.dll");//
		System.loadLibrary("Pic");//
		System.load(Dellpath); 
		JNative lib = null;
		JNative n = null;   
		Pointer p =null;
		Pointer p2= null;
	       try {  

	    	   lib = new JNative(Dellpath, "LoadLibFromFile"); 
	    	   lib.setRetVal(Type.INT);
	    	   lib.setParameter(0,Type.STRING,Libpath);//
	    	   lib.invoke(); 

	           int LibIndex = Integer.parseInt(lib.getRetVal());//����
	           System.out.println("LibIndex="+LibIndex);
	    	   
	           
	           
//	           InputStream in = getValidCode(); 
	           InputStream in = new FileInputStream(image); 
//	           ByteArrayOutputStream output = new ByteArrayOutputStream();
			      int numBytesRead = in.available();
			      byte[] b = new byte[numBytesRead];
			      in.read(b, 0, numBytesRead);
			      in.close();
			      
			      File file = new File(image);
			      int len = (int) file.length();
			      byte[] fileData = new byte[len];
			      DataInputStream dis = new DataInputStream(new FileInputStream(file));
			      dis.readFully(fileData);
			      dis.close();
			      
			      
			      
			      byte[] buf2 = FileUtils.readFileToByteArray(new File(image));
			      
			    	  System.out.println(buf2.toString().substring(3));
			    	  int i2 = Integer.parseInt(buf2.toString().substring(3), 16);
			    	    String Bin = Integer.toBinaryString(i2);
			    	    System.out.println(Bin);
			      
	            p2 = new Pointer(MemoryBlockFactory.createMemoryBlock(4*len));
				for (int i = 0; i <len; i++) {
			          p2.setIntAt(i, buf2[i]);
			    } 
				
	           n = new JNative(Dellpath, "GetCodeFromBuffer");   
	           p = new Pointer(MemoryBlockFactory.createMemoryBlock(20));//分配内存

	           p.setIntAt(0, 5000);//设置大小

	           n.setRetVal(Type.INT);   
	           int i = 0;   
	           n.setParameter(i++, Type.LONG,String.valueOf(LibIndex));   
	           n.setParameter(i++, p2);   
	           n.setParameter(i++, buf2.length);   
	           n.setParameter(i++, p);//必须使用指针 在c++中形如：‍char * info, char * state,int* len
	          
	           n.invoke();  
	           
	           String msg=new String(p.getAsString());//获取值
	          

	           System.out.println("result="+n.getRetVal());
	           System.out.println("ResultStr = "+msg);
	           msg =String.valueOf(p.getAsInt(0)) ;
	           System.out.println("msg2="+ msg);
	           if(p2!=null){
	    	    	p2.dispose();
	    	    }
	           return  result;  
	       } finally { 
	    	   if(p!=null){
	    	    	p.dispose();
	    	    }
	    	    
	    	    if(lib!=null){
	    	        lib.dispose();
	    	    }
	            if (n != null)   
	               n.dispose();   
	       }   
	}

	public static int byteArrayToInt(byte[] bytes) {
		int value = 0;
		for (int i = 0; i < 4; i++) {
			int shift = (4 - 1 - i) * 8;
			value += (bytes[i] & 0x000000FF) << shift;// 往高位游
		}
		return value;

	}

	
	  public static byte[] image2byte(String path){
		    byte[] data = null;
		    FileImageInputStream input = null;
		    try {
		      input = new FileImageInputStream(new File(path));
		      ByteArrayOutputStream output = new ByteArrayOutputStream();
		      byte[] buf = new byte[1024];
		      int numBytesRead = 0;
		      while ((numBytesRead = input.read(buf)) != -1) {
		      output.write(buf, 0, numBytesRead);
		      }
		      data = output.toByteArray();
		      output.close();
		      input.close();
		    }
		    catch (FileNotFoundException ex1) {
		      ex1.printStackTrace();
		    }
		    catch (IOException ex1) {
		      ex1.printStackTrace();
		    }
		    return data;
		  }
}
