package com.ld.tij;

public class LiftOff implements Runnable{

	protected int countDown = 10;//Default
	private static int taskCount = 0;
	private final int id = taskCount++;
	
	public LiftOff(){};
	
	public LiftOff(int countDown){
		this.taskCount = countDown;
	}
	
	public String status(){
		return "#"+id+"("+ (countDown>0?countDown:"Liftoff!") +"), ";
	}
	
	@Override
	public void run() {
//		System.out.println();
		while(countDown-->0){
			System.out.print(status());
			Thread.yield();
		}
	}

}

