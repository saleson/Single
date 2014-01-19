package com.oscache.test;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.base.CacheEntry;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.base.algorithm.AbstractConcurrentReadCache;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;
import com.opensymphony.oscache.plugins.diskpersistence.AbstractDiskPersistenceListener;
import com.single.app.component.metadate.parse.XMLParser;
import com.single.commons.util.StringUtils;

public class BaseCache extends GeneralCacheAdministrator {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static Logger logger = Logger.getLogger(XMLParser.class.getName());
	

	// 过期时间(单位为秒);
	private int refreshPeriod;
	// 关键字前缀字符;
	private String keyPrefix;

	/**
	 * 构造器
	 * 
	 * @param 关键字前缀
	 * @param 过期时间
	 */
	public BaseCache(String keyPrefix, int refreshPeriod) {
		super();
		this.keyPrefix = keyPrefix;
		this.refreshPeriod = refreshPeriod;
	}

	// 添加被缓存的对象;
	public void put(String key, Object value) {
		// 调用父类putInCache（String key, Object content）方法
		this.putInCache(this.keyPrefix + "_" + key, value);
	}
	
	// 添加被缓存的对象;
	public void put(String key, Object value,  String[] groups) {
		// 调用父类putInCache（String key, Object content）方法
		this.putInCache(this.keyPrefix + "_" + key, value, groups);
	}

	// 删除被缓存的对象;
	public void remove(String key) {
		// 调用父类flushEntry（String key）方法
		this.flushEntry(this.keyPrefix + "_" + key);
	}

	// 删除所有被缓存的对象;
	public void removeAll(Date date) {
		// 调用父类flushAll（Date date）方法
		this.flushAll(date);
	}

	public void removeAll() {
		// 调用父类flushAll（）方法
		this.flushAll();
	}

	// 获取被缓存的对象;
	public Object get(String key) throws Exception {
		try {
			return this.getFromCache(this.keyPrefix + "_" + key,
					this.refreshPeriod);
		} catch (NeedsRefreshException e) {
			
			this.cancelUpdate(this.keyPrefix + "_" + key);
			throw e;
		}
	}
	
	/**
	 * 
	 * @param groups
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Set getEnryByGroup(String... groups){
		Set<Object> entrySet = new HashSet<Object>();
		AbstractConcurrentReadCache cacheMap = getCacheMap();
		for(String group : groups){
			Set groupEntries = cacheMap.getGroup(group);
			if (groupEntries != null) {
	            Iterator itr = groupEntries.iterator();
	            String key;
	            CacheEntry entry;

	            while (itr.hasNext()) {
	                key = (String) itr.next();
	                entry = (CacheEntry) cacheMap.get(key);
	                if ((entry != null) && !entry.needsRefresh(CacheEntry.INDEFINITE_EXPIRY)) {
	                	entrySet.add(entry.getContent());
	                }
	            }
			}
		}
		return entrySet;
	}
	
	
	private AbstractConcurrentReadCache getCacheMap(){
		try {
			Field field = Cache.class.getDeclaredField("cacheMap");
			if(field!=null){
				field.setAccessible(true);
				Object obj = field.get(this.getCache());
				if(obj!=null && obj instanceof AbstractConcurrentReadCache){
					return AbstractConcurrentReadCache.class.cast(obj);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
		
        
}
