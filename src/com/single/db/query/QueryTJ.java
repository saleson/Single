package com.single.db.query;

public interface QueryTJ {
	
	
	public boolean isOr();


	public void setOr(boolean or);
	
	
	public String getCondition();


	public void setCondition(String condition);
	
	
	public void addSQL(StringBuilder buf);
}
