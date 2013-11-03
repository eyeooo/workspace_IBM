package com.toe.jiakao;
import java.io.UnsupportedEncodingException;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.pointers.Pointer;
import org.xvolks.jnative.pointers.memory.MemoryBlockFactory;


public class DellUtil {
	

	//获取索引号的名称
	public static int LoadLibFromFile(String dellPath,String dellName,String libName)throws NativeException ,IllegalAccessException{
		int result=0;
		String dellFullFile = dellPath +dellName;//获取dell的名字
		String libFullFile = dellPath + libName;//索引lib库
		System.load(dellFullFile);//加载Dell主要库
		
		JNative lib = null;
        try {  
	    	   /*
	    	    * 调用识别库索引
	    	    */
	    	   
	    	   lib = new JNative(dellName, "LoadLibFromFile"); //functionName = LoadLibFromFile
	    	   System.out.println("dellFullFile="+dellFullFile +"   functionName =LoadLibFromFile");// LoadLibFromFile
	    	   lib.setRetVal(Type.LONG);
	    	   lib.setParameter(0,Type.STRING,libFullFile);//
	    	   lib.invoke(); 

	           int LibIndex = Integer.parseInt(lib.getRetVal());//获取索引
	           
	           System.out.println("LibIndex="+LibIndex);
	    
	       
		} finally { 
	    	    if(lib!=null){
					lib.dispose();
	    	    }
	           
	       }   
		
		
	    return result;
	}
	
	//获取验证码  
	/* 图片fiel路径方式
	 * @return 若返回-1 则表示获取验证码失败
	 *         若非-1则表示验证码获取成功
	 * 
	 */
	public static int GetCodeFromFile(String dellPath,String dellName,String imageFullName,int LibIndex)throws NativeException ,IllegalAccessException,UnsupportedEncodingException{
		int result =-1;
		String dellFullFile = dellPath +dellName;//获取dell的名字
		System.load(dellFullFile);//加载Dell主要库
		

		JNative n = null;
		Pointer p =null;
		try {
			/*
			 * 调用识别库索引
			 */

			n = new JNative(dellName, "GetCodeFromFile"); // functionName =
			System.out.println("dellFullFile="+dellFullFile +"   functionName =GetCodeFromFile");// LoadLibFromFile
			p = new Pointer(MemoryBlockFactory.createMemoryBlock(20));// 分配内存20位数够用

			p.setIntAt(0, 20);// 设置大小20位整数的空间够用

			n.setRetVal(Type.INT);
			int i = 0;
			n.setParameter(i++, Type.INT, String.valueOf(1));
			n.setParameter(i++, Type.STRING, imageFullName);
			n.setParameter(i++, p);// 必须使用指针 在c++中形如：‍char * info, char *// state,int* len
			
			n.invoke();						
			result =Integer.valueOf(n.getRetVal());//1==true ,0代表false
			if(result==0)return -1;//若获取失败则返回-1。
			
	        String msg=new String(p.getAsString());//获取值
//	        System.out.println("msg = "+msg);
            
	        return Integer.valueOf(msg);
		   
		} finally {
			if (n != null) {
				n.dispose();
			}
			if(p!=null)p.dispose();//释放指针内存

	    }   
	}
	
	//获取验证码  
	/* 图片fiel路径方式
	 * @return 若返回-1 则表示获取验证码失败
	 *         若非-1则表示验证码获取成功
	 * Private Declare Function GetCodeFromBuffer Lib "Sunday.dll" 
	 * (ByVal LibFileIndex As Long,ByVal ImgBuffer As Long,ByVal ImgBufLen As Long,ByVal Code As String)
	 * 		int FileLen = (int)fsMyfile.Length;
			byte[] Buffer = new byte[FileLen]; 
	 */
	public static int GetCodeFromBuffer (String dellPath,String dellName,byte[] Buffer,int imageLen,int LibIndex)throws NativeException ,IllegalAccessException,UnsupportedEncodingException{
		int result =-1;
		String dellFullFile = dellPath +dellName;//获取dell的名字
		System.load(dellFullFile);//加载Dell主要库

		JNative n = null;
		Pointer p =null;
		Pointer p2= null;
		try {
			/*
			 * 调用识别库索引
			 */

			n = new JNative(dellName, "GetCodeFromFile"); // functionName =
			System.out.println("dellFullFile="+dellFullFile +"   functionName =GetCodeFromFile");// LoadLibFromFile
			p = new Pointer(MemoryBlockFactory.createMemoryBlock(20));// 分配内存20位数够用
            p2 = new Pointer(MemoryBlockFactory.createMemoryBlock(4*Buffer.length));//
			p.setIntAt(0, 20);// 设置大小20位整数的空间够用
			
			for (int i = 0; i <imageLen; i++) {
		          p2.setIntAt(4*i, Buffer[i]);
		    }

	
			n.setRetVal(Type.INT);
			int i = 0;
			n.setParameter(i++, Type.INT, ""+LibIndex);
			n.setParameter(i++,p2);//
			n.setParameter(i++, Type.INT, ""+imageLen);
			n.setParameter(i++, p);// 必须使用指针 在c++中形如：‍char * info, char *// state,int* len
			
			n.invoke();						
			result =Integer.valueOf(n.getRetVal());//1==true ,0代表false
			
			
	        String msg=new String(p.getAsString());//获取值
	        System.out.println("msg = "+msg);
	        
	        if(result==0)return -1;//若获取失败则返回-1。
	        return Integer.valueOf(msg);
		   
		} finally {
			if (n != null) {
				n.dispose();
			}
			if(p!=null)p.dispose();//释放指针内存

	    }   
	}
		
}
