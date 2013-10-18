package com.ld.tij;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelCopy {
	private static final int BSIZE = 1024;
	public static void main(String[] args) throws IOException {
		if(args.length!=2){
			System.out.println("args shit");
			System.exit(1);
		}
		FileChannel in = new FileInputStream("D:\\javaLD\\workspace_IBM\\IO1\\src\\com\\ld\\tij\\ChannelCopy.java").getChannel(),
		out = new FileOutputStream(args[1]).getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
		while(in.read(buffer)!=-1){
			buffer.flip();
			out.write(buffer);
			buffer.clear();
		}
		//method 2
		in.transferTo(0, in.size(), new FileOutputStream("test2.txt").getChannel());
	}

}
