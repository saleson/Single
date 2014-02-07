package com.single.db.query;


import java.util.*;

import org.apache.commons.collections4.ListUtils;


public class Where {
	
	private List<QueryTJ> querys;
	
	
	public Where(){
		querys = new ArrayList<QueryTJ>();
	}
	
	
	public List<QueryTJ> getQuerys(){
		return ListUtils.unmodifiableList(querys);
	}
	
	
	public void addQuerys(QueryTJ... tjs){
		for(QueryTJ tj : tjs){
			querys.add(tj);
		}
	}
	
	
	public boolean removeQueryTJ(QueryTJ tj){
		return querys.remove(tj);
	}
	
	
	public void removeAllQueryTJs(){
		querys.clear();
	}
	
	protected void appendConcatString(StringBuilder buf){
		buf.append(" WHERE ");
	}
	
	public void addSQL(StringBuilder buf){
		ArrayList<QueryTJ> list = new ArrayList<QueryTJ>(querys);
		checkIsOrAsFirst(list);
		appendConcatString(buf);
		addQueryString(buf, list.iterator());
	}
	
	
	private void checkIsOrAsFirst(List<QueryTJ> list){
		Iterator<QueryTJ> iter = list.iterator();
		int idx = 0;
		while(iter.hasNext()){
			QueryTJ tj = iter.next();
			if(!tj.isOr()){
				if(idx>0){
					iter.remove();
					list.add(0, tj);
				}
				break;
			}
			idx++;
		}
	}
	
	private void addQueryString(StringBuilder buf, Iterator<QueryTJ> tjs){
		int idx = 0;
		while(tjs.hasNext()){
			QueryTJ tj = tjs.next();
			buf.append(idx>0 ? tj.isOr() ? " OR " : " AND " : "");
			tj.addSQL(buf);
			idx++;
		}
	}
	
}