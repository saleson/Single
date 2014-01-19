package com.single.cache;

public class CacheManager {
	private BaseCache cache;

	private static CacheManager instance;
	private static Object lock = new Object();

	public CacheManager() {
		// 这个根据配置文件来，初始BaseCache;
		cache = new BaseCache("", 1);
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
	 * 根据学生ID将缓存中的学生对象移除
	 * 
	 * @param newsID
	 *            ：学生ID
	 */
	public void removeCaches(String key) {
		cache.remove(key);
	}

	/**
	 * 删除缓存中所有的学生对象
	 */
	public void removeAll() {
		cache.removeAll();
	}

}
