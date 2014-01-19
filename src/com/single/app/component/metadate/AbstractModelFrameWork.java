package com.single.app.component.metadate;

public abstract class AbstractModelFrameWork implements ModelFramework {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	protected String groupName;
	protected boolean isLatest = true;
	
	
	public AbstractModelFrameWork(String groupName){
		this.groupName = groupName;
	}
	
	@Override
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	@Override
	public String getGroupName() {
		return groupName;
	}
	
	@Override
	public boolean isLatest() {
		return isLatest;
	}
	
	@Override
	public void isLatest(boolean isLatest) {
		this.isLatest = isLatest;
	}
}
