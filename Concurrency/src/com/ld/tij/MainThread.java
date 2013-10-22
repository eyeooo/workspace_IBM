package com.ld.tij;

public class MainThread {

	public static void main(String[] args) {
//		new LiftOff().run();
//		new LiftOff().run();
//		new LiftOff().run();
		
//		Thread t = new Thread(new LiftOff());
//		t.start();
//		System.out.println("\nWaitting for LiftOff");
		 
		for(int i=0;i<5;i++){
			new Thread(new LiftOff()).start();
		}
		System.out.println("\nWaitting MORE for LiftOff");
		
	}

}
