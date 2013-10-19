package com.ld.tij.xfiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ThawAlien {

	public static void main(String[] args) throws IOException, Exception {
		// TODO Auto-generated method stub
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("bin//com//ld//tij", "X.flie")));
		Object o = in.readObject();
		System.out.println(o.getClass());
	}

}
