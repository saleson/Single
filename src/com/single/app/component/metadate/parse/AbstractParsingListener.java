package com.single.app.component.metadate.parse;

public abstract class AbstractParsingListener implements ParsingListener{

	private boolean deprecated = false;
	
	/**
	 * 设置是否过时,过时的监听器将不会被调用.
	 * @param deprecated
	 */
	public void setDeprecated(boolean deprecated){
		this.deprecated = deprecated;
	}
	
	/**
	 * 返回该监听器是否过时,过时的监听器将不会被调用.
	 * @return
	 */
	public boolean isDeprecated(){
		return deprecated;
	}
}
