package com.single.cache;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.base.CacheEntry;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.base.algorithm.AbstractConcurrentReadCache;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;
import com.single.app.component.metadate.parse.XMLParser;

public class BaseCache extends GeneralCacheAdministrator{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static Logger logger = Logger.getLogger(BaseCache.class.getName());
	
	protected int refreshPeriod;	//过期时间(单位为秒);
	protected String keyPrefix;		// 关键字前缀字符;

	/**
	 * 构造器
	 * 
	 * @param 关键字前缀
	 * @param 过期时间
	 */
	public BaseCache(String keyPrefix, int refreshPeriod) {
		this.keyPrefix = keyPrefix;
		this.refreshPeriod = refreshPeriod;
	}

	/**
	 * 添加被缓存的对象
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value) {
		// 调用父类putInCache（String key, Object content）方法
		this.putInCache(this.keyPrefix + "_" + key, value);
	}
	
	/**
	 * 添加被缓存的对象
	 * @param key
	 * @param value
	 * @param groups 该对象所属的组
	 */
	public void put(String key, Object value, String[] groups) {
		// 调用父类putInCache（String key, Object content）方法
		this.putInCache(this.keyPrefix + "_" + key, value, groups);
	}

	/**
	 * 删除被缓存的对象
	 * @param key
	 */
	public void remove(String key) {
		// 调用父类flushEntry（String key）方法
		this.flushEntry(this.keyPrefix + "_" + key);
	}

	/**
	 * 删除指定时间前的所有被缓存的对象
	 * @param date
	 */
	public void removeAll(Date date) {
		// 调用父类flushAll（Date date）方法
		this.flushAll(date);
	}
	
	/**
	 * 删除指定时间前的所有被缓存的对象
	 * @param date
	 */
	public void removeGroup(String group) {
		// 调用父类flushGroup(group)方法
		this.flushGroup(group);
	}

	/**
	 * 删除所有被缓存的对象
	 */
	public void removeAll() {
		// 调用父类flushAll（）方法
		this.flushAll();
	}

	/**
	 * 获取被缓存的对象
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Object get(String key) throws NeedsRefreshException {
		return get(key, Object.class);
	}
	
	/**
	 * 返回一组驻留在一个特定组的缓存内容.
	 * 
	 * @param groups
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Set getEnryByGroups(String... groups){
		Set<Object> entrySet = new HashSet<Object>();
		AbstractConcurrentReadCache cacheMap = getCacheMap();
		if(cacheMap!=null){
			for(String group : groups){
				Set groupEntries = cacheMap.getGroup(group);
				if (groupEntries != null) {
			    	Iterator itr = groupEntries.iterator();
			    	while (itr.hasNext()) {
			    		String key = (String) itr.next();
			    		CacheEntry entry = (CacheEntry) cacheMap.get(key);
		                if ((entry != null) && !entry.needsRefresh(CacheEntry.INDEFINITE_EXPIRY)) {
		                	entrySet.add(entry.getContent());
		                }
		            }
				}
			}
		}
		return entrySet;
	}
	
	/**
	 * 返回AbstractConcurrentReadCache.
	 * 通过java映射实现.
	 * @return
	 */
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
			logger.info("通过java映射获取AbstractConcurrentReadCache失败!");
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 获取被缓存的对象
	 * @param key
	 * @param clazz
	 * @return
	 * @throws NeedsRefreshException
	 */
	public <T> T get(String key, Class<T> clazz) throws NeedsRefreshException{
		Object content = null;
		try {
			content = this.getFromCache(this.keyPrefix + "_" + key, this.refreshPeriod);
		} catch (NeedsRefreshException nre) {
			try {
				if(nre.getCacheContent()==null){
					put(key, null);
					return null;
				}
				
		        T t = refreshCache(key, clazz, nre.getCacheContent());
		        if(t != null){
		        	this.putInCache(key, t);
		        	return t;
		        }
		    } catch (Exception ex) {
		    	this.cancelUpdate(this.keyPrefix + "_" + key);
		    }
			logger.error("缓存内容['" + key + "']已过期,你可以通过异常对象NeedsRefreshException.getCacheContent()获取已过期的缓存内容.");
			throw nre;
		}
		if(content!=null){
			if(clazz.isInstance(content)){
				return clazz.cast(content);
			}else{
				throw new ClassCastException("The cache content '" + key + "' of the  class is " + content.getClass().getName() + ",can not cast of the class " + clazz.getName());
			}
		}
		return null;
	}
	
	/**
	 * 在获取缓存对象的过期时，刷新缓存对象的方法.
	 * @param key 缓存对象的key
	 * @param clazz 缓存对象的类型
	 * @return
	 */
	public <T> T refreshCache(String key, Class<T> clazz, Object cacheContent){
		return null;
	}
}
