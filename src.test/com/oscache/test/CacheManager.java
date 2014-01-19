package com.oscache.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

public class CacheManager {
	private com.single.cache.BaseCache studentsCache;

	private static CacheManager instance;
	private static Object lock = new Object();

	public CacheManager() {
		// 这个根据配置文件来，初始BaseCache;
		studentsCache = new com.single.cache.BaseCache("students", -1);
		try {
			System.out.println(BeanUtils.getProperty(studentsCache.getCache().getPersistenceListener(), "root"));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * 单例化一个CacheManager
	 * 
	 * @return CacheManager对象
	 */
	public static CacheManager getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new CacheManager();
				}
			}
		}
		return instance;
	}

	/**
	 * 将学生对象存入缓存中
	 * 
	 * @param news
	 *            ：学生对象
	 */
	public void putStudents(Students students) {
		studentsCache.put(students.getID(), students);
	}
	
	/**
	 * 将学生对象存入缓存中
	 * 
	 * @param news
	 *            ：学生对象
	 */
	public void putStudents(Students students, String[] groups) {
		studentsCache.put(students.getID(), students, groups);
	}

	/**
	 * 根据学生ID将缓存中的学生对象移除
	 * 
	 * @param newsID
	 *            ：学生ID
	 */
	public void removeStudents(String studentsID) {
		studentsCache.remove(studentsID);
	}

	/**
	 * 根据学生ID从缓存中提取对应的学生对象
	 * 
	 * @param newsID
	 *            ：学生ID
	 * @return：学生对象
	 */
	public Students getStudents(String studentsID) {
		try {
			return (Students) studentsCache.get(studentsID);
		} catch (Exception e) {
			// 自动生成 catch 块
			System.out.println("getStudents>>studentsID[" + studentsID + "]>>"
					+ e.getMessage());
			Students students = new Students(studentsID);
			//this.putStudents(students);
			return students;
		}
	}
	
	public Set<Students> getStudentByGroup(String[] groups){
		return studentsCache.getEnryByGroups(groups);
	}

	/**
	 * 删除缓存中所有的学生对象
	 */
	public void removeAllStudents() {
		studentsCache.removeAll();
	}

}
