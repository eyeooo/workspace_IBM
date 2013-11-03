package com.toe.jiakao;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class TestDell {
	
	public static void main(String[] args) {
		
		
		String path = "G:\\Lib\\";
		
		String imageFullName = path+"ok.GIF";
		
		InputStream is  =null;
		ByteArrayOutputStream out =null;
		try {
//			getCodeFormFile(path,path);
			int LibIndex =DellUtil.LoadLibFromFile(path, "ok.dll", "ok.lib");  //索引
			
			//直接file image文件形式
			int verifyCode = DellUtil.GetCodeFromFile(path, "ok.dll", imageFullName, LibIndex);//code
			System.out.println("verifyCode1="+verifyCode);
			//字节流形式
		    is  = new  FileInputStream(imageFullName);
		    out = new ByteArrayOutputStream(1024); 
		    byte[] temp = new byte[1024]; //1kb
		    int length =0;
		    int imagelen=0;
		    while((length=is.read(temp))!=-1){
		    	out.write(temp, 0, length);  
		    }
		    byte[] Buffer = out.toByteArray();   
		    is.close(); //关闭 
//		    GetCodeFromBuffer (String dellPath,String dellName,byte[] Buffer,int imageLen,int LibIndex)
			//直接file image文件形式
		    System.out.println("Buffer="+Buffer.length);
			int verifyCode2 = DellUtil.GetCodeFromBuffer(path, "ok.dll", Buffer,Buffer.length, LibIndex);//code
			
			System.out.println("verifyCode2="+verifyCode2);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error");
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				is=null;
			}
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out = null;
			}
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	//dell的接口
//	
////	Private Declare Function GetCodeFromFile Lib "Sunday.dll" (ByVal LibFileIndex As Long,ByVal FilePath As String,ByVal Code As String) As Boolean
//
//	
//	private static boolean getCodeFormFile(String Dellpath,String Libpath)throws Exception{
//		int result  = 0;
//		boolean BooleanResult=false;
//		Dellpath += "ok.dll";
//		Libpath= "G:\\Lib\\ok.lib";
//		String image ="G:\\Lib\\ok.GIF";
////		System.load("G:/Lib/Pic.dll");//
//		System.load(Dellpath);  
////		System.loadLibrary("演示.dll");
//	
//		System.out.println("Dellpath="+Dellpath);
//		
////		System.load(Dellpath); 
////		GetCodeFromFile(Dellpath,Libpath,Code);
////		return result;
//		JNative lib = null;
//		JNative n = null;   
//		Pointer p =null;
//	       try {  
//	    	   
//	    	   /*
//	    	    * 调用识别库索引
//	    	    */
//	    	   
//	    	   lib = new JNative("ok.dll", "LoadLibFromFile"); 
//	    	   lib.setRetVal(Type.LONG);
//	    	   lib.setParameter(0,Type.STRING,Libpath);//
//	    	   lib.invoke(); 
//
//	           int LibIndex = Integer.parseInt(lib.getRetVal());//索引
//	           System.out.println("LibIndex="+LibIndex);
//	
//	           
//	           n = new JNative("ok.dll", "GetCodeFromFile");   
//	           p = new Pointer(MemoryBlockFactory.createMemoryBlock(10));//分配内存
//
//	           p.setIntAt(0, 6);//设置大小
//
//	           n.setRetVal(Type.INT);   
//	           int i = 0;   
//	           n.setParameter(i++, Type.INT,String.valueOf(1));   
//	           n.setParameter(i++, Type.STRING,image);   
//	           n.setParameter(i++, p);//必须使用指针 在c++中形如：‍char * info, char * state,int* len
//	          
//	           n.invoke();  
//	           
//	           String msg=new String(p.getAsString());//获取值
//	          
//
//	           result =Integer.valueOf(n.getRetVal());//1==true ,0代表false
//	           if(result==0)BooleanResult=false;
//	           if(result==1)BooleanResult=true;
//	           System.out.println("result="+result);
//	           System.out.println("ResultStr = "+msg);
//	           msg =String.valueOf(p.getAsInt(0)) ;
//	           System.out.println("msg2="+ msg+"size="+p.getSize());
//	           return  BooleanResult;  
//	       } finally { 
//	    	    if(p!=null){
//	    	    	p.dispose();
//	    	    }
//	    	    if(lib!=null){
//	    	        lib.dispose();
//	    	    }
//	            if (n != null)   
//	               n.dispose();   
//	       }   
//	}
}
