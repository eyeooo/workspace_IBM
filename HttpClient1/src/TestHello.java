import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;


public class TestHello {
 
static	String libPath = "D:\\javaLD\\workspace_IBM\\HttpClient1\\bin\\ys.lib";
static	String image = "D:\\javaLD\\workspace_IBM\\HttpClient1\\bin\\vcode.gif";
  static {
     System.load("D:\\javaLD\\workspace_IBM\\HttpClient1\\bin\\TestHello.dll");
     System.load("D:\\javaLD\\workspace_IBM\\HttpClient1\\bin\\ys.dll");
     System.load("D:\\javaLD\\workspace_IBM\\HttpClient1\\bin\\pic.dll");
  }
 
  public static native void hello(String msg);
  public static native String fuckcode(long lib, byte[] buf, long buf_len, String libPath);
  
  public static void main(String[] args) throws IOException {
    
	  byte[] buf = FileUtils.readFileToByteArray(new File(image));
	  
//     hello("Hello,Kimm!");
    byte[] b = new byte[]{1,2,3,4};
    String s = fuckcode(1,buf,buf.length,libPath);
    System.out.println(s);
    
  }
}