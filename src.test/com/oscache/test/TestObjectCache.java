package com.oscache.test;

import java.util.Set;

public class TestObjectCache {
	public static void main(String args[]) {
		long t = System.currentTimeMillis();
		// 实例化一个学生对象
		Students student = new Students();
		// 设置相关属性
		String id = "fdd\\sf/dks/001_disk";
		student.setID(id);
		student.setName("张三");
		student.setScore("A");
		
		Students student1 = new Students();
		// 设置相关属性
		String id1 = "/fjd/001_dis";
		student1.setID(id);
		student1.setName("张三");
		student1.setScore("A");

		String[] groups = {"AA"};
		String[] groups1 = {"BB"};
		String[] group = {"AA","BB"};
		CacheManager.getInstance().putStudents(student, groups);
		CacheManager.getInstance().putStudents(student1, groups1);
		// 从缓存中删除所有的学生对象
		// CacheManager.getInstance().removeAllStudents();
		// 从缓存中删除ID为a001的学生
		// CacheManager.getInstance().removeStudents("a001");
		// 从缓存中取得ID为a001的学生
		/*try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		for(int i=0;i<2;i++){
			Students s = (Students) CacheManager.getInstance().getStudents(id);
			System.out.println("Students'name is " + s.getName());
			System.out.println("Students'score is " + s.getScore());
		}
		
		Set<Students> set = CacheManager.getInstance().getStudentByGroup(group);
		System.out.println(set.size());
		
		// 取得该学生的名字与成绩
		//System.out.println("Students'name is " + s.getName());
		//System.out.println("Students'score is " + s.getScore());
		long e = System.currentTimeMillis();
		System.out.println(e-t);
	}

}
