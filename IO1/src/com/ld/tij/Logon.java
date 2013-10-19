package com.ld.tij;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Logon implements Serializable {
	
	private Date date = new Date();
	private String username;
	private transient String password;
	
	public Logon(String name, String pwd){
		this.username=name;
		this.password=pwd;
	}
	public String toString(){
		return "logon info: \n username: "+username+"\n date: "+date+"\n password: "+password;
	}

	public static void main(String[] args) throws  IOException, Exception {
		Logon a = new Logon("Dog", "lucky");
		System.out.println("Logon a="+a);
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Logon.out"));
		o.writeObject(a);
		o.close();
		TimeUnit.SECONDS.sleep(1);
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("Logon.out"));
		System.out.println("Recovering objec at "+ new Date());
		a=(Logon) in.readObject();
		System.out.println("logon a="+a);
	}

}
