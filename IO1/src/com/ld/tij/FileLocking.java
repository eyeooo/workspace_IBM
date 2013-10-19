package com.ld.tij;

import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

public class FileLocking {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		FileOutputStream fos = new FileOutputStream("file.txt");
		FileLock fl = fos.getChannel().tryLock();
		if(fl!=null){
			System.out.println("Locked File");
			TimeUnit.MILLISECONDS.sleep(1000);
			fl.release();
			System.out.println("Released Lock");
		}
		fos.close();
	}

}
