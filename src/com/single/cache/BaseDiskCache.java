package com.single.cache;

import java.io.File;
import java.lang.reflect.Field;
import com.opensymphony.oscache.base.persistence.PersistenceListener;
import com.opensymphony.oscache.plugins.diskpersistence.AbstractDiskPersistenceListener;
import com.opensymphony.oscache.plugins.diskpersistence.DiskPersistenceListener;
import com.single.commons.util.StringUtils;


/**
 * 用于OSCache将缓存对象缓存到硬盘上的类型.
 * 可以通过setCachePath(path)方法另外设置缓存的相对路径.
 * @author Saleson. 
 * Computer by Administrator.
 *
 */
public class BaseDiskCache extends BaseCache{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String DEFAULT_CACHE_ROOTDIRECTORY = "cacheDirectory/oscache";
	public static final String CACHE_ROOTDIRECTORY_KEY = "root";
	
	/**
	 * 创建一个使用硬盘做缓存区的缓存管理对象.
	 * @param keyPrefix 缓存对象的前缀
	 */
	public BaseDiskCache(String keyPrefix) {
		this(keyPrefix, new DiskPersistenceListener());
	}
	
	/**
	 * 创建一个使用硬盘做缓存区的缓存管理对象.
	 * @param keyPrefix 缓存对象的前缀
	 * @param persistenceListener 缓存的监听器,用于将缓存对象持久化到硬盘上.
	 */
	public BaseDiskCache(String keyPrefix, PersistenceListener persistenceListener){
		super(keyPrefix, -1);
		setPersistenceListener(persistenceListener);
	}
	
	/**
	 * 创建一个使用硬盘做缓存区的缓存管理对象.
	 * @param keyPrefix 缓存对象的前缀
	 * @param cachePath 缓存区的相对路径
	 */
	public BaseDiskCache(String keyPrefix, String cachePath){
		this(keyPrefix);
		setCachePath(cachePath);
	}
	
	/**
	 * 创建一个使用硬盘做缓存区的缓存管理对象.
	 * @param keyPrefix 缓存对象的前缀
	 * @param persistenceListener 缓存的监听器,用于将缓存对象持久化到硬盘上.
	 * @param cachePath 缓存区的相对路径
	 */
	public BaseDiskCache(String keyPrefix, PersistenceListener persistenceListener, String cachePath){
		this(keyPrefix, persistenceListener);
		setCachePath(cachePath);
	}
	
	
	/**
	 * 重新配置缓存的监听器,将监听器中的各个属性初始化赋值.
	 * @param persistenceListener
	 */
	private void setPersistenceListener(PersistenceListener persistenceListener) {
		this.getCache().setPersistenceListener(persistenceListener.configure(config));
	}
	
	
	
	/**
	 * 重新设置硬盘缓存区的相对路径.
	 * @param path
	 */
	public void setCachePath(String path){
		PersistenceListener persistenceListener = getCache().getPersistenceListener();
		if(persistenceListener!=null && StringUtils.isNotEmpty(path)){
			String rootPath = StringUtils.defaultString(getProperty(AbstractDiskPersistenceListener.CACHE_PATH_KEY), DEFAULT_CACHE_ROOTDIRECTORY);
			File rootDirectory = new File(rootPath, path);
			try {
				Field field = AbstractDiskPersistenceListener.class.getDeclaredField(CACHE_ROOTDIRECTORY_KEY);
				field.setAccessible(true);
				field.set(persistenceListener, rootDirectory.getPath());
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}
}
