package com.oscache.test;

import java.io.File;

import com.opensymphony.oscache.base.persistence.CachePersistenceException;
import com.opensymphony.oscache.plugins.diskpersistence.DiskPersistenceListener;

public class SingleDiskPersistenceListener extends DiskPersistenceListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void store(String key, Object obj) throws CachePersistenceException {
		if(key.endsWith("disk"))
			super.store(key, obj);
	}
	
	@Override
	protected void store(File file, Object obj) throws CachePersistenceException {
		super.store(file, obj);
	}
	
	
}
