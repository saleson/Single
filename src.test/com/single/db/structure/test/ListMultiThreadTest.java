package com.single.db.structure.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ListMultiThreadTest {

	public static class NThread extends Thread{
		private List<String> list;
		private String name;
		private long[] time;
		
		public NThread(String name, long[] time){
			this.name = name;
			this.time = time;
		}
		
		public void setList(List<String> list){
			this.list = list;
		}
		
		@Override
		public void run() {
			long l1 = System.currentTimeMillis();
			for(int i=0;i<10000;i++){
				list.add("" + i);
			}
			long l2 = System.currentTimeMillis();
			long t = (l2 - l1);
			this.time[0] = this.time[0] + t;
			System.out.println(this.name + ":" + t);
			System.out.println("总" + this.name + ":" + this.time[0]);
		}
	}
	
	public static class MThread extends Thread{
		private List<String> list;
		private String name;
		private long[] time;
		
		public MThread(String name, long[] time){
			this.name = name;
			this.time = time;
		}
		
		public void setList(List<String> list){
			this.list = list;
		}
		
		@Override
		public void run() {
			long l1 = System.currentTimeMillis();
			for(int i=0;i<list.size();i++){
				list.get(i);
			}
			long l2 = System.currentTimeMillis();
			long t = (l2 - l1);
			this.time[0] = this.time[0] + t;
			System.out.println(this.name + ":" + t);
			System.out.println("总" + this.name + ":" + this.time[0]);
		}
	}
	
	public static void add(List<String> list){
		for(int i=0;i<30000;i++){
			list.add("" + i);
		}
	}
	
	public static void start(String name, List<String> list, long[] time){
		for(int i=0;i<10;i++){
			NThread n = new NThread(name, time);
			n.setList(list);
			n.start();
			
			MThread m = new MThread(name, time);
			m.setList(list);
			m.start();
		}
	}
	
	
	public static void main(String[] args) {
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new Vector<String>();
		String name1 = "name1", name2 = "name2";
		long[] t1 = {0L}, t2 = {0L};
		//add(list1);
		//add(list2);
		start(name2, list2, t2);
		start(name1, list1, t1);
	}
	
	
}
