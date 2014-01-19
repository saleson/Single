package com.single.cache;

import com.opensymphony.oscache.base.persistence.CachePersistenceException;
import com.opensymphony.oscache.plugins.diskpersistence.DiskPersistenceListener;

/**
 * 
 * @author Saleson. 
 * Computer by Administrator.
 *
 */
public abstract class SingleDiskPersistenceListener extends DiskPersistenceListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void store(String key, Object obj) throws CachePersistenceException {
		if(beStored(key, obj))
			super.store(key, obj);
	}
	
	public abstract boolean beStored(String key, Object obj);
}
