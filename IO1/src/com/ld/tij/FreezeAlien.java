package com.ld.tij;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class FreezeAlien {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("bin//com//ld//tij//X.flie"));
		Alien quellek = new Alien();
		out.writeObject(quellek);
	}

}

class Alien implements Serializable{}
