package com.ld.tij;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.mindview.util.Countries;


public class SlowMap<K,V> extends AbstractMap<K,V> {

	private List<K> keys = new ArrayList<K>();
	private List<V> values = new ArrayList<V>();
	public V put(K key, V value){
		V oldValue=get(key);
		if(!keys.contains(key)){
			keys.add(key);
			values.add(value);
		}else{
			values.set(keys.indexOf(key), value);
		}
		return oldValue;
	}
	public V get(Object key){
		if(!keys.contains(key))return null;
		return values.get(keys.indexOf(key));
	}
	
	public static void main(String[] args) {
		SlowMap<String,String> m = new SlowMap<String,String>();
		m.putAll(Countries.capitals(15));
		System.out.println(m);
//		m.put("BURUNDI", "SHIT");
		System.out.println(m.get("BURUNDI"));
		System.out.println(m.entrySet());
		System.out.println(m.entrySet().contains(new MapEntry<String,String>("ALGERIA", "Algiers")));
	} 

	
	
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K,V>>();
		Iterator<K> ki = keys.iterator();
		Iterator<V> vi = values.iterator();
		while(ki.hasNext()){
			set.add(new MapEntry<K,V>(ki.next(), vi.next()));
		}
		return set;
	}

}

class MapEntry<K,V> implements Map.Entry<K, V>{
	private K key;
	private V value;
	public MapEntry(K key, V valus){
		this.key=key;
		this.value=valus;
	}
	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V v) {
		V result = value;
		value = v;
		return result;
	}
	
	public int hashCode(){
		return (key==null?0:key.hashCode())^(value==null?0:value.hashCode());
	}
	public boolean equals(Object o){
		if(!(o instanceof MapEntry)) return false;
		MapEntry me = (MapEntry)o;
		return (key==null? me.getKey()==null:key.equals(me.getKey())) 
				&& (value==null?me.getValue()==null:value.equals(me.getValue()));
	}
	public String toString(){
		return key + "-" + value;
	}
}
