package com.single.app.component.metadate;

import java.io.Serializable;


public interface ModelFramework extends Serializable,Cloneable{

	/**
	 * 
	 * @param contextName
	 */
	public void setGroupName(String groupName);
	
	/**
	 * 
	 * @return
	 */
	public String getGroupName();
	
	
	public String getName();
	
	/**
	 * 是否需要组装分析
	 * @return
	 */
	public boolean needAssembly();
	
	/**
	 * 返回这个模型框架是否是最新的.
	 * @return
	 */
	public boolean isLatest();
	
	/**
	 * 设置是否最新
	 * @param isLatest
	 */
	public void isLatest(boolean isLatest);
}
