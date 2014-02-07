package com.single.db.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class QueryCondition implements QueryTJ{
	
	private String condition;
	private boolean or;
	private Collection<QueryItem> queryItems;
	
	
	public QueryCondition(String condition){
		this(condition, false);
	}
	
	public QueryCondition(String condition, boolean isOr){
		this.condition = condition;
		this.or = isOr;
		queryItems = new HashSet<QueryItem>();
	}
	
	
	public String getCondition() {
		return condition;
	}
	
	
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	
	public boolean isOr() {
		return or;
	}
	
	
	public void setOr(boolean or) {
		this.or = or;
	}
	
	
	public Iterator<QueryItem> getQueryItems(){
		return queryItems.iterator();
	}
	
	
	public void addQueryItems(QueryItem... items){
		for(QueryItem item : items){
			queryItems.add(item);
		}
	}
	
	
	public boolean removeQueryItem(QueryItem item){
		return queryItems.remove(item);
	}
	
	
	public void removeAllQueryItems(){
		queryItems.clear();
	}

	@Override
	public void addSQL(StringBuilder buf) {
		ArrayList<QueryItem> list = new ArrayList<QueryItem>(queryItems);
		checkIsOrAsFirst(list);
		buf.append("(");
		addQueryString(buf, list.iterator());
		buf.append(")");
		
	}
	
	private void checkIsOrAsFirst(List<QueryItem> list){
		Iterator<QueryItem> iter = list.iterator();
		int idx = 0;
		while(iter.hasNext()){
			QueryItem item = iter.next();
			if(!item.isOr()){
				if(idx>0){
					iter.remove();
					list.add(0, item);
				}
				break;
			}
			idx++;
		}
	}
	
	private void addQueryString(StringBuilder buf, Iterator<QueryItem> items){
		int idx = 0;
		while(items.hasNext()){
			QueryItem item = items.next();
			buf.append(idx>0 ? item.isOr() ? " OR " : " AND " : "");
			item.addSQL(buf);
			idx++;
		}
	}

	
}
