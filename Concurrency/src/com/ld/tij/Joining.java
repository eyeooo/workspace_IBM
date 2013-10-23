package com.ld.tij;

class Sleeper extends Thread{
	private int duration;
	public Sleeper(String name, int sleeptime){
		super(name);
		duration = sleeptime;
		start();
	}
	public void run(){
		try {
			sleep(duration);
		} catch (InterruptedException e) {
			System.out.println(getName()+" was interrupted. "+"isInterrupted():"+isInterrupted());
		}
		System.out.println(getName()+" has awakened");
	}
}

class Joiner extends Thread{
	private Sleeper sleeper;
	public Joiner(String name, Sleeper sleeper){
		super(name);
		this.sleeper=sleeper;
		start();
	}
	public void run(){
		try {
			sleeper.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(getName()+" join completed");
	}
}

public class Joining {

	public static void main(String[] args) {
		Sleeper sleeper = new Sleeper("Sleepy", 1500);
		Sleeper grumpy = new Sleeper("Grumpy", 1500);
		Joiner dopey = new Joiner("Dopey",sleeper);
		Joiner doc = new Joiner("Doc", grumpy);
		grumpy.interrupt();
	}

}
