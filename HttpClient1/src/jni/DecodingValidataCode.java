package jni;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;


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
    
	  byte[] buf = FileUtils.readFileToByteArray(new File(image));
	  
//	  hello("Hello, Money!");
	  
//	  String s = GetCodeFromFile(image, libPath);
//	  System.out.println(s);
	  String s1 = GetCodeFromBuffer(buf, buf.length, libPath);
	  System.out.println(s1);
    
  }
}