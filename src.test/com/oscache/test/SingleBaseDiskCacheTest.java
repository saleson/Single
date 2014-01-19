package com.oscache.test;

import java.util.Map;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.single.cache.BaseDiskCache;

public class SingleBaseDiskCacheTest {

	public static void test1(){
		BaseDiskCache cache = new BaseDiskCache("");
		cache.setCachePath("sjkd");
	}
	
	public static void test2(){
		//long t = System.currentTimeMillis();
		// 实例化一个学生对象
		Students student = new Students();
		// 设置相关属性
		String id = "001_disk";
		student.setID(id);
		student.setName("张三");
		student.setScore("A");
		
		BaseDiskCache cache = new BaseDiskCache("TS", "public");
		//cache.put(id, student);
		try {
			Students s = (Students) cache.get(id);
			System.out.println("Students'name is " + s.getName());
			System.out.println("Students'score is " + s.getScore());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void test3() throws NeedsRefreshException{
		TestT t = new TestT();
		t.put("name", "testT001");
		t.put("age", "32");
		
		Students student = new Students();
		String id = "001_disk";
		student.setID(id);
		student.setName("张三");
		student.setScore("A");
		student.setTestT(t);
		System.out.println("\n\n\n11111111111111111111111");
		printTest3(student);
		
		Students student1 = new Students();
		String id1 = "002_disk";
		student1.setID(id1);
		student1.setName("李四");
		student1.setScore("B");
		student1.setTestT(t);
		printTest3(student1);
		
		BaseDiskCache cache = new BaseDiskCache("TS", "public");
		//cache.put(id, student);
		//cache.put(id1, student1);
		
		System.out.println("\n\n\n222222222222222222222");
		student = cache.get(id, Students.class);
		printTest3(student);
		
		//cache.put(id, student);
		student1 = cache.get(id1, Students.class);
		printTest3(student1);
		TestT tt = student.getTestT();
		tt.put("name", "TESTTTTT");
		tt.put("age", "222");
		//cache.put(id1, student1);
		
		System.out.println("\n\n\n333333333333333333333");
		student = cache.get(id, Students.class);
		printTest3(student);
		student1 = cache.get(id1, Students.class);
		printTest3(student1);
		
		System.out.println("\n\n\n");
		System.out.println((t==tt));
		Map<String, String> map = t.getMap();
		for(String key : map.keySet()){
			System.out.println("map." + key + ":" + map.get(key));
		}
	}
	
	public static void printTest3(Students student){
		System.out.println("id:" + student.getID());
		System.out.println("name:" + student.getName());
		System.out.println("score:" + student.getScore());
		TestT t = student.getTestT();
		if(t!=null){
			Map<String, String> map = t.getMap();
			for(String key : map.keySet()){
				System.out.println("map." + key + ":" + map.get(key));
			}
		}
		System.out.println("\n=======================================================");
	}
	
	
	public static void main(String[] args) throws NeedsRefreshException {
		test3();
	}
}
