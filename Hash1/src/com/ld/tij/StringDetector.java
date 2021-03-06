package com.ld.tij;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


class Groundhog {

	protected int number;
	public Groundhog(int n){
		number=n;
	}
	public String toString(){
		return "Groundhog #"+number;
	}
	
}

class Groundhog2 extends Groundhog{
	public Groundhog2 (int n){
		super(n);
	}
	public int hashCode(){
		return number;
	}
	public boolean equals(Object o){
		return o instanceof Groundhog2 && (number == ((Groundhog2)o).number);
	}
}

class Prediction{
	private static  Random rand = new Random(47);
	private boolean shadow=rand.nextDouble()>0.5;
	public String toString(){
		if(shadow){
			return "six more week of winter!";
		}else{
			return "early spring!";
		}
	}
}

public class StringDetector{
	public static <T extends Groundhog> void detectSpring(Class<T> type) throws Exception{
		Constructor<T> ghog = type.getConstructor(int.class);
		Map<Groundhog,Prediction> map = new HashMap<Groundhog,Prediction>();
		for(int i=0;i<10;i++){
			map.put(ghog.newInstance(i), new Prediction());
		}
		System.out.println("map= "+map);
		
		Groundhog gh = ghog.newInstance(3);
		System.out.println("Looking for prediction for " + gh);
		if(map.containsKey(gh)){
			System.out.println(map.get(gh));
		}else{
			System.out.println("Key not found: "+gh);
		}
	}
	public static void main(String[] args) throws Exception{
//		detectSpring(Groundhog.class);
		detectSpring(Groundhog2.class);
	}
}