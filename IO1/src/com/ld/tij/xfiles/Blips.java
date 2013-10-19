package com.ld.tij.xfiles;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

class Blip1 implements Externalizable{
	public Blip1(){
		System.out.println("Blip1 Con");
	}

	@Override
	public void readExternal(ObjectInput arg0) throws IOException,
			ClassNotFoundException {
		System.out.println("Blip1 readExternal");
	}

	@Override
	public void writeExternal(ObjectOutput arg0) throws IOException {
		System.out.println("Blip1 writeExternal");
	}
	
}

class Blip2 implements Externalizable{
	public Blip2(){
		System.out.println("Blip2 Con");
	}

	@Override
	public void readExternal(ObjectInput arg0) throws IOException,
			ClassNotFoundException {
		System.out.println("Blip2 readExternal");
	}

	@Override
	public void writeExternal(ObjectOutput arg0) throws IOException {
		System.out.println("Blip2 writeExternal");
	}
	
}

public class Blips {

	public static void main(String[] args) throws IOException, Exception {
		System.out.println("Constructing Objects:");
		Blip1 b1 = new Blip1();
		Blip2 b2 = new Blip2();
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Blips.out"));
		System.out.println("Saving Objects:");
		o.writeObject(b1);
		o.writeObject(b2);
		o.close();
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("Blips.out"));
		System.out.println("Recovering b1:");
		b1 = (Blip1) in.readObject();
		System.out.println("Recovering b2:");
		b2 = (Blip2) in.readObject();
		
	}

}
