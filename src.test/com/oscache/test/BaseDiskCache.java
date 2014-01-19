package com.oscache.test;

public class BaseDiskCache extends BaseCache{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BaseDiskCache(String keyPrefix) {
		super(keyPrefix, -1);
	}
	
}
