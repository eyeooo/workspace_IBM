package com.ld.tij;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {

	public static void main(String[] args) {
		
		cachedThreadPool();
//		fixedThreadPool();
//		singleThreadExecutor();
	}


	public static void cachedThreadPool(){
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i=0;i<5;i++){
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}

	public static void fixedThreadPool() {
		ExecutorService exec = Executors.newFixedThreadPool(5);
		for(int i=0;i<5;i++){
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}
	
	public static void singleThreadExecutor(){
		ExecutorService exec = Executors.newSingleThreadExecutor();
		for(int i=0;i<5;i++){
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}

}