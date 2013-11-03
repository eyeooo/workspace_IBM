// jni_DecodingValidataCode.cpp : Defines the entry point for the DLL application.
//

#include "stdafx.h"
#include <jni.h>
#include "jni_DecodingValidataCode.h"
#include <stdio.h>
#include <string.h>
#include <iostream>

BOOL APIENTRY DllMain( HANDLE hModule, DWORD  ul_reason_for_call, LPVOID lpReserved)
{
    return TRUE;
}

jstring str2jstring(JNIEnv* env,const char* pat)
{
    jclass strClass = (env)->FindClass("Ljava/lang/String;");
    jmethodID ctorID = (env)->GetMethodID(strClass, "<init>", "([BLjava/lang/String;)V");
    jbyteArray bytes = (env)->NewByteArray(strlen(pat));
    (env)->SetByteArrayRegion(bytes, 0, strlen(pat), (jbyte*)pat);
    jstring encoding = (env)->NewStringUTF("UTF-8"); 
    return (jstring)(env)->NewObject(strClass, ctorID, bytes, encoding);
}

char* Jstring2CStr(JNIEnv* env, jstring jstr)  
{  
	char* rtn = NULL;
	jclass clsstring = env->FindClass("java/lang/String");   
	jstring strencode = env->NewStringUTF("UTF-8");  
	jmethodID mid = env->GetMethodID(clsstring, "getBytes", "(Ljava/lang/String;)[B");   
	jbyteArray barr= (jbyteArray)env->CallObjectMethod(jstr,mid,strencode);  
	jsize alen = env->GetArrayLength(barr);
	jbyte* ba = env->GetByteArrayElements(barr,JNI_FALSE);  
	if(alen > 0)  
	{  
	rtn = (char*)malloc(alen+1); //new   char[alen+1];  
	memcpy(rtn,ba,alen);
	rtn[alen]=0;  
	}  
	env->ReleaseByteArrayElements(barr,ba,0);  

	return rtn;
} 

JNIEXPORT void JNICALL Java_jni_DecodingValidataCode_hello
  (JNIEnv * env, jclass obj, jstring jMsg)
{
	 const char *strMsgPtr = env->GetStringUTFChars(jMsg , 0);  
     MessageBox( 0, strMsgPtr,"Message box from VC++ ", 0 );
     env->ReleaseStringUTFChars( jMsg, strMsgPtr); 
}

int getLib(JNIEnv* env,jstring lib_path, HINSTANCE hInst)
{
	char* libpath = Jstring2CStr(env,lib_path);

	typedef int (CALLBACK* LPLoadLib)(char[]);
	LPLoadLib LoadLibFromFile = (LPLoadLib)GetProcAddress(hInst, "LoadLibFromFile");

	int index = LoadLibFromFile (libpath);
	if (index == -1)
	{
		std::cout<<"Failed load the xx.lib!";
		return -1;
	}else
	{
		return index;
	}
}

JNIEXPORT jstring JNICALL Java_jni_DecodingValidataCode_GetCodeFromFile
  (JNIEnv * env, jclass obj, jstring image_path, jstring lib_path)
{
	HINSTANCE hInst = LoadLibraryA("ys.dll");
	if (!hInst)
	{
		std::cout<<"Failed load the ys.dll!";
		return 0;
	}

	int index = getLib(env, lib_path, hInst);
	if(index == -1)
	{
		return 0;
	}

	typedef bool (CALLBACK* LPGetCode)(int,char[],char[]);
	LPGetCode GetCodeFromFile = (LPGetCode)GetProcAddress(hInst, "GetCodeFromFile");
	
 
	char result[5];
	char* image = Jstring2CStr(env,image_path);
	if (GetCodeFromFile(index, image, result))
		std::cout<<"decoded : "<<result;
	else
		std::cout<<"decoded failed!";
	image = NULL;

	char* rp = result;
	jstring j = str2jstring(env, rp);
	rp=NULL;
	return j;
}

JNIEXPORT jstring JNICALL Java_jni_DecodingValidataCode_GetCodeFromBuffer
  (JNIEnv * env, jclass obj, jbyteArray buf, jlong buf_len, jstring lib_path)
{
	HINSTANCE hInst = LoadLibraryA("ys.dll");
	if (!hInst)
	{
		std::cout<<"Failed load ys.dll!";
		return 0;
	}

	int index = getLib(env, lib_path, hInst);
	if(index == -1)
	{
		return 0;
	}

	typedef bool (CALLBACK* LPGetCode)(int,char[],int,char[]);
	LPGetCode GetCodeFromBuffer = (LPGetCode)GetProcAddress(hInst, "GetCodeFromBuffer");
	
 
	char result[5];
	char* data = (char*)env->GetByteArrayElements(buf, 0);  
	if (GetCodeFromBuffer(index,data, buf_len,result))
		std::cout<<"decoded: "<<result;
	else
		std::cout<<"decoded failed!";

	char* rp = result;
	jstring j = str2jstring(env, rp);
	rp=NULL;
	return j;
}
