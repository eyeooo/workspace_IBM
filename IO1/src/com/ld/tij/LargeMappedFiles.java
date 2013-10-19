package com.ld.tij;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class LargeMappedFiles {

	static int length = 0x8FFFFFF; //128MB
	
	public static void main(String[] args) throws IOException, Exception {
		// TODO Auto-generated method stub
		MappedByteBuffer out 
			= new RandomAccessFile("test.dat","rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length);
		for(int i=0;i<length;i++){
			out.put((byte)'x');
		}
		System.out.println("Finished writing");
		for(int i=length/2;i<length/2+60;i++){
			System.out.print((char)out.get(i));
		}
	}

}
